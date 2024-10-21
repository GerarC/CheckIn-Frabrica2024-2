package com.fabrica.CheckIn.controller;

import com.fabrica.CheckIn.model.ContactoEmergencia;
import com.fabrica.CheckIn.repository.ContactoEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ContactoEmergenciaGraphqlController {

    @Autowired
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @QueryMapping
    public List<ContactoEmergencia> listarContactoEmergencia() {
        return contactoEmergenciaRepository.findAll();
    }

    @MutationMapping
    public ContactoEmergencia guardarContactoEmergencia(@Argument ContactoEmergencia contactoEmergencia){
        return contactoEmergenciaRepository.save(contactoEmergencia);
    }
}
