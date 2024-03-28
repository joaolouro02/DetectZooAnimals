package com.example.MicroservicoFrontEnd.proxys;

import com.example.MicroservicoFrontEnd.modulos.UtilizadorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("MICROSERVICO-UTILIZADORES")
public interface ProxyMicroservicoUtilizadores {

    @GetMapping("utilizadorExiste/{email}/{password}")
    boolean utilizadorExiste(@PathVariable String email, @PathVariable String password);

    @GetMapping("/utilizador/ID/{email}")
    long getId(@PathVariable String email);

    @GetMapping("/utilizador/nome/{email}")
    String getNome(@PathVariable String email);

    @GetMapping("/utilizador/role/{email}")
    String getRole(@PathVariable String email);

    @GetMapping("/utilizador/imagem/{email}/{fileName}/{idTarefa}")
    String uploadImagem(@PathVariable String email,@PathVariable String fileName, @PathVariable String idTarefa);

    @GetMapping("emailUsado/{email}")
    boolean emailUsado(@PathVariable String email);

    @GetMapping("/utilizadores/")
    List<UtilizadorDTO> getAll();

    @PostMapping("/utilizador/{email}")
    void deleteUtilizador(@PathVariable String email);

    @PostMapping("/utilizador")
    void save(UtilizadorDTO user);
}
