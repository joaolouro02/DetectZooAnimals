package com.example.Microservicoanimais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioAnimal extends JpaRepository<Animal,Long> {
}
