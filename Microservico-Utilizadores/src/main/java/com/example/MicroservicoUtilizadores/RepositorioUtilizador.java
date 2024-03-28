package com.example.MicroservicoUtilizadores;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface RepositorioUtilizador extends JpaRepository<Utilizador,Long> {

    Utilizador findByEmail(String email);

    Utilizador findUtilizadorByEmailAndPassword(String email, String password);
    Utilizador findUtilizadorByEmail(String email);


    @Query("SELECT u FROM Utilizador u")
    List<Utilizador> findAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Utilizador u WHERE u.email=?1")
    void deleteByEmail(String email);
}
