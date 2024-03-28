package com.example.MicroservicoFrontEnd.modulos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String nomeAnimal;

    public AnimalDTO(String animalName) {
        this.nomeAnimal=animalName;
    }
}
