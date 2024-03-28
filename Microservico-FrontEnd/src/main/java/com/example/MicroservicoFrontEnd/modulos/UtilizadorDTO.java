package com.example.MicroservicoFrontEnd.modulos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilizadorDTO {


    private Long id;
    private String nome;
    private String email;
    private String password;
    private String role;

}
