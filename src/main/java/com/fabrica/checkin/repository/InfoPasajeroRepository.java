package com.fabrica.checkin.repository;

import com.fabrica.checkin.model.InfoPasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPasajeroRepository extends JpaRepository<InfoPasajero, Long> {
}
