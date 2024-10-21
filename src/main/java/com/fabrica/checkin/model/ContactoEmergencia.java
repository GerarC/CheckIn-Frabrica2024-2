package com.fabrica.checkin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Generated
public class ContactoEmergencia {
    @Id @GeneratedValue
    private Long idContacto;

    private String nombreCompleto;

    private String telefono;

    private String email;
}
