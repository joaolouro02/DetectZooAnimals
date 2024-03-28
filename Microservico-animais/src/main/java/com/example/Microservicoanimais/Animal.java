package com.example.Microservicoanimais;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAnimal;
    private String imgURL;

    public Animal(String nomeAnimal, String imgURL) {
        this.nomeAnimal = nomeAnimal;
        this.imgURL=imgURL;
    }
}
