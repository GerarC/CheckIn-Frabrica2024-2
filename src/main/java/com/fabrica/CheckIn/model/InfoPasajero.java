package com.fabrica.CheckIn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InfoPasajero {

    @Id @GeneratedValue
    private Long IdPasajero;

    @ManyToOne
    private ContactoEmergencia ContactoEmergencia;

    private String nacionalidad;

    private String motivoViaje;

    private String direccionEntregarMaletaPerdia;

    private boolean checkIn;
}
