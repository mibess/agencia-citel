package com.citel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citel.api.models.candidato.Candidato;

@Repository
public interface TesteRepository extends JpaRepository<Candidato, Long> {

}
