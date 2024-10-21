package com.fabrica.CheckIn.repository;

import com.fabrica.CheckIn.model.InfoPasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPasajeroRepository extends JpaRepository<InfoPasajero, Long> {
}
