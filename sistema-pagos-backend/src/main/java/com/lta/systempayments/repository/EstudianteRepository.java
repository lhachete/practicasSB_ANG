package com.lta.systempayments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lta.systempayments.entities.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,String> {

    Estudiante findByCodigo(String codigo);

    List<Estudiante> findByProgramaId (String programaId);

}
