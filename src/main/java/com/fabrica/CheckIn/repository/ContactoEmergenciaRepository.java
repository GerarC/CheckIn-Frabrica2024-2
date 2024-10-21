package com.fabrica.CheckIn.repository;

import com.fabrica.CheckIn.model.ContactoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoEmergenciaRepository extends JpaRepository<ContactoEmergencia, Long> {
    ContactoEmergencia findByNombreCompletoAndTelefonoAndEmail(String nombreCompleto, String telefono, String email);
}
