package com.fabrica.checkin.controller;

import com.fabrica.checkin.model.ContactoEmergencia;
import com.fabrica.checkin.repository.ContactoEmergenciaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@GraphQlTest(ContactoEmergenciaGraphqlController.class)
class ContactoEmergenciaGraphqlControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @Test
    void testListarContactoEmergencia() {
        // Given
        ContactoEmergencia contacto1 = ContactoEmergencia.builder()
                .idContacto(1L)
                .nombreCompleto("Juan Perez")
                .telefono("123456789")
                .email("juan.perez@example.com")
                .build();

        ContactoEmergencia contacto2 = ContactoEmergencia.builder()
                .idContacto(2L)
                .nombreCompleto("Maria Gomez")
                .telefono("987654321")
                .email("maria.gomez@example.com")
                .build();

        Mockito.when(contactoEmergenciaRepository.findAll()).thenReturn(Arrays.asList(contacto1, contacto2));

        // When & Then
        graphQlTester.document("{ listarContactoEmergencia { idContacto nombreCompleto telefono email } }")
                .execute()
                .path("listarContactoEmergencia")
                .entityList(ContactoEmergencia.class)
                .hasSize(2)
                .satisfies(contactos -> {
                    ContactoEmergencia c1 = contactos.get(0);
                    assertEquals(1L, c1.getIdContacto());
                    assertEquals("Juan Perez", c1.getNombreCompleto());
                    assertEquals("123456789", c1.getTelefono());
                    assertEquals("juan.perez@example.com", c1.getEmail());

                    // Assertions for contacto2
                    ContactoEmergencia c2 = contactos.get(1);
                    assertEquals(2L, c2.getIdContacto());
                    assertEquals("Maria Gomez", c2.getNombreCompleto());
                    assertEquals("987654321", c2.getTelefono());
                    assertEquals("maria.gomez@example.com", c2.getEmail());
                });
    }


    @Test
    void testGuardarContactoEmergencia() {
        // Given
        ContactoEmergencia contacto = ContactoEmergencia.builder()
                .idContacto(1L)
                .nombreCompleto("Juan Perez")
                .telefono("123456789")
                .email("juan.perez@example.com")
                .build();

        Mockito.when(contactoEmergenciaRepository.save(Mockito.any(ContactoEmergencia.class))).thenReturn(contacto);

        // When & Then
        String mutation = "mutation { guardarContactoEmergencia(contactoEmergencia: {" +
                " nombreCompleto: \"Juan Perez\", telefono: \"123456789\", email: \"juan.perez@example.com\" })" +
                " { idContacto nombreCompleto telefono email } }";

        var response = graphQlTester.document(mutation).execute();

        assertNotNull(response.path("guardarContactoEmergencia"));

        response.path("guardarContactoEmergencia.idContacto")
                .entity(Long.class)
                .isEqualTo(1L)
                .path("guardarContactoEmergencia.nombreCompleto")
                .entity(String.class)
                .isEqualTo("Juan Perez")
                .path("guardarContactoEmergencia.telefono")
                .entity(String.class)
                .isEqualTo("123456789")
                .path("guardarContactoEmergencia.email")
                .entity(String.class)
                .isEqualTo("juan.perez@example.com");
    }

}
