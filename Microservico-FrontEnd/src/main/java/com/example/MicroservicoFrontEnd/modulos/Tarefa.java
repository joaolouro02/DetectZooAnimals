package com.example.MicroservicoFrontEnd.modulos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarefa {

    @Id
    private String id;

    private String state;

    private String fileName;

    private String imageHash;

    private String identifiedObjects;

    private Long userId;


    private LocalDateTime inicio;
    private LocalDateTime fim;
    private Duration duracao;

    /*public void setTemposDeTarefa(Integer inferenceMs, Integer processMs, Integer analysisRoundTripMs) {
        this.inicio = Duration.ofMillis(inferenceMs);
        this.fim = Duration.ofMillis(processMs);
        this.duracao = Duration.ofMillis(analysisRoundTripMs);
    }*/

    public void setId(String id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setImageHash(String imageHash) {
        this.imageHash = imageHash;
    }

   /* public void setIdentifiedObjects(String identifiedObjects) {
        this.identifiedObjects = identifiedObjects;
    }*/



    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }


    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }


    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

}