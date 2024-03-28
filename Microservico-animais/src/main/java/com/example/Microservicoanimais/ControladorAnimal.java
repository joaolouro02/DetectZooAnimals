package com.example.Microservicoanimais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ControladorAnimal {

    @Autowired
    private RepositorioAnimal repositorioAnimal;

    @GetMapping("/nomesAnimais")
    public Map<String, String> getNomeAnimais(){
        List<Animal> animais = repositorioAnimal.findAll();
        Map<String, String> nomesAnimais = new HashMap<>();
        for (Animal animal : animais) {
            nomesAnimais.put(animal.getNomeAnimal(), animal.getImgURL());
        }
        return nomesAnimais;
    }
}


