package com.fabrica.CheckIn.controller;

import com.fabrica.CheckIn.model.ContactoEmergencia;
import com.fabrica.CheckIn.model.InfoPasajero;
import com.fabrica.CheckIn.repository.ContactoEmergenciaRepository;
import com.fabrica.CheckIn.repository.InfoPasajeroRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Controller
public class InfoPasajeroGraphqlController {

    @Autowired
    private InfoPasajeroRepository infoPasajeroRepository;

    @Autowired
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @Operation(summary = "mostrar todos los pasajeros")
    @QueryMapping
    public List<InfoPasajero> listarPasajeros() {
        return infoPasajeroRepository.findAll();
    }

    @Operation(summary = "mostrar todos los pasajeros")
    @MutationMapping
    public InfoPasajero guardarInfoPasajero(@Argument InfoPasajero infoPasajero) {

        ContactoEmergencia contacto = infoPasajero.getContactoEmergencia();

        // Buscar si ya existe un contacto de emergencia con esos datos (nombre, teléfono, email)
        ContactoEmergencia contactoExistente = contactoEmergenciaRepository
                .findByNombreCompletoAndTelefonoAndEmail(contacto.getNombreCompleto(),
                        contacto.getTelefono(), contacto.getEmail());
        if (contactoExistente != null) {
            // Si ya existe, asociarlo con el pasajero
            infoPasajero.setContactoEmergencia(contactoExistente);
        } else {
            // Si no existe, guardarlo en la base de datos
            contactoEmergenciaRepository.save(contacto);
        }
        // Guardar el pasajero con el contacto de emergencia asociado
        return infoPasajeroRepository.save(infoPasajero);
    }

    @Operation(summary = "mostrar todos los pasajeros")
    @MutationMapping
    public InfoPasajero confirmarCheckIn(@Argument Long idPasajero) {
        // Buscar al pasajero por ID
        InfoPasajero infoPasajero = infoPasajeroRepository.findById(idPasajero)
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado"));

        // Cambiar el valor de checkIn a true
        infoPasajero.setCheckIn(true);

        // Guardar los cambios en la base de datos
        return infoPasajeroRepository.save(infoPasajero);
    }


}
