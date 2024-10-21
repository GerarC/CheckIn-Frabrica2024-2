package com.fabrica.checkin.controller;

import com.fabrica.checkin.model.ContactoEmergencia;
import com.fabrica.checkin.model.InfoPasajero;
import com.fabrica.checkin.repository.ContactoEmergenciaRepository;
import com.fabrica.checkin.repository.InfoPasajeroRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InfoPasajeroGraphqlController {

    private final InfoPasajeroRepository infoPasajeroRepository;
    private final ContactoEmergenciaRepository contactoEmergenciaRepository;

    @Operation(summary = "mostrar todos los pasajeros")
    @QueryMapping
    public List<InfoPasajero> listarPasajeros() {
        return infoPasajeroRepository.findAll();
    }

    @Operation(summary = "mostrar todos los pasajeros")
    @MutationMapping
    public InfoPasajero guardarInfoPasajero(@Argument InfoPasajero infoPasajero) {

        ContactoEmergencia contacto = infoPasajero.getContactoEmergencia();

        ContactoEmergencia contactoExistente = contactoEmergenciaRepository
                .findByNombreCompletoAndTelefonoAndEmail(contacto.getNombreCompleto(),
                        contacto.getTelefono(), contacto.getEmail());
        if (contactoExistente != null) {
            infoPasajero.setContactoEmergencia(contactoExistente);
        } else {
            contactoEmergenciaRepository.save(contacto);
        }
        return infoPasajeroRepository.save(infoPasajero);
    }

    @Operation(summary = "mostrar todos los pasajeros")
    @MutationMapping
    public InfoPasajero confirmarCheckIn(@Argument Long idPasajero) {
        InfoPasajero infoPasajero = infoPasajeroRepository.findById(idPasajero)
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));

        infoPasajero.setCheckIn(true);

        return infoPasajeroRepository.save(infoPasajero);
    }

}
