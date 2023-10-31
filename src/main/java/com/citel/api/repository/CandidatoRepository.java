package com.citel.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.citel.api.models.candidato.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

}
