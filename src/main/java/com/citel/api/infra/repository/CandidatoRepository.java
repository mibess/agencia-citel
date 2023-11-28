package com.citel.api.infra.repository;

import org.springframework.stereotype.Repository;

import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.dto.CandidatoPorEstadoDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

  @Query("SELECT NEW com.citel.api.domain.candidato.dto.CandidatoPorEstadoDTO(COUNT(c) AS quantidade, c.endereco.estado AS estado) FROM Candidato c GROUP BY c.endereco.estado")
  List<CandidatoPorEstadoDTO> findCandidatosByEstado();

  List<Candidato> findBySexo(String string);

  List<Candidato> findByPesoGreaterThan(Long peso);

  Optional<Candidato> findByCpf(String cpf);

  long count();

}
