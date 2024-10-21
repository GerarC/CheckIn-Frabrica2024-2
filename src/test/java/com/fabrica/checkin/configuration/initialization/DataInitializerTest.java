package com.fabrica.checkin.configuration.initialization;

import com.fabrica.checkin.model.ContactoEmergencia;
import com.fabrica.checkin.model.InfoPasajero;
import com.fabrica.checkin.repository.ContactoEmergenciaRepository;
import com.fabrica.checkin.repository.InfoPasajeroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataInitializerTest {

    @Autowired
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @Autowired
    private InfoPasajeroRepository infoPasajeroRepository;

    @Test
    void testDataInitializer() {
        List<ContactoEmergencia> contactos = contactoEmergenciaRepository.findAll();
        assertThat(contactos).hasSize(1);
        ContactoEmergencia contacto = contactos.get(0);
        assertThat(contacto.getIdContacto()).isEqualTo(1L);
        assertThat(contacto.getNombreCompleto()).isEqualTo("juanito");
        assertThat(contacto.getTelefono()).isEqualTo("123");
        assertThat(contacto.getEmail()).isEqualTo("jaja@gmail.com");

        List<InfoPasajero> pasajeros = infoPasajeroRepository.findAll();
        assertThat(pasajeros).hasSize(10);

        for (InfoPasajero pasajero : pasajeros) {
            assertThat(pasajero.getContactoEmergencia()).isNotNull();
            assertThat(pasajero.getContactoEmergencia().getIdContacto()).isEqualTo(1L);
            assertThat(pasajero.getNacionalidad()).isNotNull();
            assertThat(pasajero.getMotivoViaje()).isNotNull();
        }
    }
}
