package com.citel.api.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citel.api.http.dto.CandidatoPorEstadoDTO;
import com.citel.api.models.candidato.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

  @Query("SELECT NEW com.citel.api.http.dto.CandidatoPorEstadoDTO(COUNT(c) AS quantidade, c.endereco.estado AS estado) FROM Candidato c GROUP BY c.endereco.estado")
  List<CandidatoPorEstadoDTO> findCandidatosByEstado();

  List<Candidato> findBySexo(String string);

  List<Candidato> findByPesoGreaterThan(Long peso);

  Optional<Candidato> findByCpf(String cpf);

  long count();

}
