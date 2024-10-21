package com.fabrica.checkin.controller;

import com.fabrica.checkin.model.ContactoEmergencia;
import com.fabrica.checkin.repository.ContactoEmergenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContactoEmergenciaGraphqlController {

    private final ContactoEmergenciaRepository contactoEmergenciaRepository;

    @QueryMapping
    public List<ContactoEmergencia> listarContactoEmergencia() {
        return contactoEmergenciaRepository.findAll();
    }

    @MutationMapping
    public ContactoEmergencia guardarContactoEmergencia(@Argument ContactoEmergencia contactoEmergencia){
        return contactoEmergenciaRepository.save(contactoEmergencia);
    }
}
