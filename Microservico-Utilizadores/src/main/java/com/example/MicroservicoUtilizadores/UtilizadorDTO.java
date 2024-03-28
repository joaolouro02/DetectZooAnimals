package com.example.MicroservicoUtilizadores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilizadorDTO {

    private  Long id;
    private String nome;
    private String email;
    private String password;
    private String role;


}
