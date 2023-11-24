package com.digitalhouse.clinicaOdontologica.repository;

import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Odontologo findByMatricula(Integer matricula);
}
