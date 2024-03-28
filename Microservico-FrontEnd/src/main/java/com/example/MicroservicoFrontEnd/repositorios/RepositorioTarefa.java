package com.example.MicroservicoFrontEnd.repositorios;

import com.example.MicroservicoFrontEnd.modulos.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositorioTarefa extends JpaRepository<Tarefa, String> {

    Tarefa findByImageHash(String imageHash);

    @Query("SELECT t.fileName FROM Tarefa t WHERE t.id = :id")
    String findFileNameById(@Param("id") String id);

    Tarefa findTarefaById(String tarefaId);

    @Query("SELECT t FROM Tarefa t WHERE t.userId = :userId")
    List<Tarefa> findTarefasByUserId(Long userId);
}
