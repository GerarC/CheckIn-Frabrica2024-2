package com.fabrica.checkin.controller;

import com.fabrica.checkin.model.ContactoEmergencia;
import com.fabrica.checkin.model.InfoPasajero;
import com.fabrica.checkin.repository.ContactoEmergenciaRepository;
import com.fabrica.checkin.repository.InfoPasajeroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@GraphQlTest(InfoPasajeroGraphqlController.class)
class InfoPasajeroGraphqlControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private InfoPasajeroRepository infoPasajeroRepository;

    @MockBean
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @Test
    void testListarPasajeros() {
        // Given
        InfoPasajero pasajero1 = InfoPasajero.builder()
                .idPasajero(1L)
                .nacionalidad("Colombiana")
                .motivoViaje("Vacaciones")
                .checkIn(false)
                .build();
        InfoPasajero pasajero2 = InfoPasajero.builder()
                .idPasajero(2L)
                .nacionalidad("Peruana")
                .motivoViaje("Negocios")
                .checkIn(true)
                .build();
        Mockito.when(infoPasajeroRepository.findAll()).thenReturn(Arrays.asList(pasajero1, pasajero2));

        // When & Then
        graphQlTester.document("{ listarPasajeros { idPasajero nacionalidad motivoViaje checkIn } }")
                .execute()
                .path("listarPasajeros")
                .entityList(InfoPasajero.class)
                .hasSize(2)
                .matches(pasajeros -> pasajeros.stream().anyMatch(p ->
                        p.getIdPasajero() == 1L &&
                                p.getNacionalidad().equals("Colombiana") &&
                                p.getMotivoViaje().equals("Vacaciones") &&
                                !p.isCheckIn()
                ))
                .matches(pasajeros -> pasajeros.stream().anyMatch(p ->
                        p.getIdPasajero() == 2L &&
                                p.getNacionalidad().equals("Peruana") &&
                                p.getMotivoViaje().equals("Negocios") &&
                                p.isCheckIn()
                ));
    }


    @Test
    void testGuardarInfoPasajero() {
        // Given
        ContactoEmergencia contacto = ContactoEmergencia.builder()
                .idContacto(1L)
                .nombreCompleto("Juan Perez")
                .telefono("123456789")
                .email("juan.perez@example.com")
                .build();

        InfoPasajero infoPasajero = InfoPasajero.builder()
                .idPasajero(1L)
                .contactoEmergencia(contacto)
                .nacionalidad("Colombiana")
                .motivoViaje("Vacaciones")
                .direccionEntregarMaletaPerdia("Calle 123")
                .checkIn(false)
                .build();

        Mockito.when(contactoEmergenciaRepository.save(Mockito.any(ContactoEmergencia.class))).thenReturn(contacto);
        Mockito.when(infoPasajeroRepository.save(Mockito.any(InfoPasajero.class))).thenReturn(infoPasajero);

        // When & Then
        String mutation = "mutation { guardarInfoPasajero(infoPasajero: { contactoEmergencia: { nombreCompleto: \"Juan Perez\", telefono: \"123456789\", email: \"juan.perez@example.com\" }, nacionalidad: \"Colombiana\", motivoViaje: \"Vacaciones\", direccionEntregarMaletaPerdia: \"Calle 123\" }) { idPasajero nacionalidad motivoViaje checkIn } }";

        graphQlTester.document(mutation)
                .execute()
                .path("guardarInfoPasajero.idPasajero")
                .entity(Long.class)
                .isEqualTo(1L)
                .path("guardarInfoPasajero.nacionalidad")
                .entity(String.class)
                .isEqualTo("Colombiana")
                .path("guardarInfoPasajero.motivoViaje")
                .entity(String.class)
                .isEqualTo("Vacaciones")
                .path("guardarInfoPasajero.checkIn")
                .entity(Boolean.class)
                .isEqualTo(false);
    }

    @Test
    void testConfirmarCheckIn() {
        // Given
        InfoPasajero infoPasajero = InfoPasajero.builder()
                .idPasajero(1L)
                .checkIn(false)
                .build();

        Mockito.when(infoPasajeroRepository.findById(1L)).thenReturn(Optional.of(infoPasajero));
        Mockito.when(infoPasajeroRepository.save(any(InfoPasajero.class))).thenReturn(infoPasajero);

        // When & Then
        String mutation = "mutation { confirmarCheckIn(idPasajero: 1) { idPasajero checkIn } }";
        graphQlTester.document(mutation)
                .execute()
                .path("confirmarCheckIn.checkIn")
                .entity(Boolean.class)
                .isEqualTo(true);

        Mockito.verify(infoPasajeroRepository).save(infoPasajero);
    }
}
