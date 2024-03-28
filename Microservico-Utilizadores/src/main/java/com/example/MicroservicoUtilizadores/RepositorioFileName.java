package com.example.MicroservicoUtilizadores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioFileName extends JpaRepository<FileName,Long> {

}
