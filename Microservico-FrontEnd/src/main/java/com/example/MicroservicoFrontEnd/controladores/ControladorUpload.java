package com.example.MicroservicoFrontEnd.controladores;

import com.example.MicroservicoFrontEnd.CodeProjectAIResponse;
import com.example.MicroservicoFrontEnd.proxys.ProxyMicroservicoUtilizadores;
import com.example.MicroservicoFrontEnd.modulos.Tarefa;
import com.example.MicroservicoFrontEnd.repositorios.RepositorioTarefa;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class ControladorUpload {

    @Autowired
    private RepositorioTarefa repositorioTarefa;

    @Autowired
    private ProxyMicroservicoUtilizadores proxyMicroservicoUtilizadores;


    private final Path root = Paths.get("uploads");



    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    private final String codeProjectAIEndpoint = "http://localhost:32168/v1/vision/detection";

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestPart("imagem") MultipartFile file, RedirectAttributes attributes, Model model, HttpSession session) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("mensagem", "Por favor selecione o arquivo para upload.");
            return "redirect:/";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Tarefa tarefa=new Tarefa();
        try {

            Path path = Paths.get(this.root + "/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            model.addAttribute("path", "/images/" + file.getOriginalFilename());

            String fileHash = DigestUtils.md5Hex(Files.readAllBytes(path));
            Tarefa existingTarefa = repositorioTarefa.findByImageHash(fileHash);

            if(existingTarefa!=null){
                repositorioTarefa.delete(existingTarefa);
            }

            // Crie uma nova tarefa
            tarefa.setId(String.valueOf(UUID.randomUUID()));
            tarefa.setState("Em processamento");
            tarefa.setFileName(fileName);
            tarefa.setImageHash(fileHash);
            tarefa.setInicio(LocalDateTime.now());

            // Configurar as partes do formulário
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });
            body.add("use_alphamatting", false);

            // Configurar os cabeçalhos
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Configurar a entidade da solicitação
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Faça uma solicitação POST para o endpoint do serviço CodeProjectAI
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CodeProjectAIResponse> responseEntity = restTemplate.postForEntity(codeProjectAIEndpoint, requestEntity, CodeProjectAIResponse.class);

            // Processar a resposta, se necessário
            CodeProjectAIResponse response = responseEntity.getBody();

            // Extrair o nome do animal
            String animalName = response.getPredictions().get(0).getLabel();

            tarefa.setIdentifiedObjects(animalName);
            tarefa.setFim(LocalDateTime.now());
            tarefa.setDuracao(Duration.between(tarefa.getInicio(), tarefa.getFim()));
            tarefa.setState("Concluída");

            model.addAttribute("file",fileName);

            model.addAttribute("animalName", animalName);

            String email = (String) session.getAttribute("email");
            long id =proxyMicroservicoUtilizadores.getId(email);
            tarefa.setUserId(id);

            proxyMicroservicoUtilizadores.uploadImagem(email,fileName,String.valueOf(UUID.randomUUID()));

            repositorioTarefa.save(tarefa);

            return "result";

        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("mensagem", "Erro durante o upload do arquivo.");
            // Atualize o estado da tarefa para "Cancelada"
            tarefa.setState("Cancelada");
            repositorioTarefa.save(tarefa);
            return "redirect:/";
        }
    }


}