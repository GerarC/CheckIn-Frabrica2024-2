package com.fabrica.checkin.model;

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
@Generated
public class InfoPasajero {

    @Id @GeneratedValue
    private Long idPasajero;

    @ManyToOne
    private ContactoEmergencia contactoEmergencia;

    private String nacionalidad;

    private String motivoViaje;

    private String direccionEntregarMaletaPerdia;

    private boolean checkIn;
}
