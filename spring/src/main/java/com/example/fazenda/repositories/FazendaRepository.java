package com.example.fazenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fazenda.entities.Fazenda;

public interface FazendaRepository extends JpaRepository<Fazenda, Long> {
    
}
