package com.example.MicroservicoUtilizadores;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ControladorUtilizador {

    @Qualifier("codificador.bcrypt")
    @Autowired
    private PasswordEncoder codificador;

    @Autowired
    private RepositorioUtilizador repositorioUtilizador;

    @Autowired
    private RepositorioFileName repositorioFileName;

    @GetMapping("/utilizador/imagem/{email}/{fileName}/{idTarefa}")
    public String uploadImagem(@PathVariable String email,@PathVariable String fileName,@PathVariable String idTarefa){

        Utilizador utilizador=repositorioUtilizador.findByEmail(email);
        FileName fileName1=new FileName();
        fileName1.setFileName(fileName);
        fileName1.setIdTarefa(idTarefa);
        utilizador.getFileNames().add(fileName1);
        repositorioFileName.save(fileName1);
        repositorioUtilizador.save(utilizador);

        return "Utilizador salvo";
    }

    @GetMapping("utilizadorExiste/{email}/{password}")
    public boolean utilizadorExiste(@PathVariable String email, @PathVariable String password){
        Utilizador user = repositorioUtilizador.findByEmail(email);
        if (user == null)
            return false;
        return codificador.matches(password,user.getPassword());
    }

    @GetMapping("emailUsado/{email}")
    public boolean emailUsado(@PathVariable String email){

        return repositorioUtilizador.findUtilizadorByEmail(email) == null;
    }

    @GetMapping("/utilizador/ID/{email}")
    public long getId(@PathVariable String email){

        Utilizador utilizador=repositorioUtilizador.findByEmail(email);
        return utilizador.getId();
    }

    @GetMapping("/utilizador/nome/{email}")
    public String getNome(@PathVariable String email){

        Utilizador utilizador=repositorioUtilizador.findByEmail(email);
        return utilizador.getNome();
    }


    @GetMapping("/utilizador/role/{email}")
    public String getRole(@PathVariable String email){
        Utilizador utilizador = repositorioUtilizador.findByEmail(email);
        if(utilizador != null) {
            Collection<? extends GrantedAuthority> authorities = utilizador.getAuthorities();
            if(authorities != null && !authorities.isEmpty()) {
                return authorities.iterator().next().getAuthority();
            }
        }
        return "ROLE_STANDARD";
    }



    @GetMapping("/utilizadores/")
    public List<UtilizadorDTO> getAll(){
        List<UtilizadorDTO> users = new ArrayList<>();
        for(Utilizador u : repositorioUtilizador.findAll()){
            users.add(new UtilizadorDTO(u.getId(),u.getNome(),u.getEmail(),u.getPassword(),u.getRole()));
        }

        return users;
    }


    @PostMapping("/utilizador")
    public void save(@RequestBody UtilizadorDTO user){
        repositorioUtilizador.save(new Utilizador(user.getNome(), user.getEmail(), codificador.encode(user.getPassword())));
    }

    @PostMapping("/utilizador/{email}")
    public void deleteUtilizador(@PathVariable String email) {
        Utilizador utilizador = repositorioUtilizador.findByEmail(email);
        if (utilizador != null) {
            List<FileName> fileNames = utilizador.getFileNames();
            repositorioFileName.deleteAll(fileNames);
            repositorioUtilizador.delete(utilizador);
        }
    }

}
