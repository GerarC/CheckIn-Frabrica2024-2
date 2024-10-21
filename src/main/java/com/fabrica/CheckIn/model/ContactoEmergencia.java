package com.fabrica.CheckIn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContactoEmergencia {

    @Id @GeneratedValue
    private Long idContacto;

    private String nombreCompleto;

    private String telefono;

    private String email;

    public ContactoEmergencia() {
    }
}
