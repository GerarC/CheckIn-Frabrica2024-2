package com.fabrica.checkin.configuration.initialization;

import com.fabrica.checkin.configuration.Generated;
import com.fabrica.checkin.model.ContactoEmergencia;
import com.fabrica.checkin.model.InfoPasajero;
import com.fabrica.checkin.repository.ContactoEmergenciaRepository;
import com.fabrica.checkin.repository.InfoPasajeroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner commandLineRunner(ContactoEmergenciaRepository contactoEmergenciaRepository,
                                        InfoPasajeroRepository infoPasajeroRepository) {
        return args -> {
            ContactoEmergencia contact = new ContactoEmergencia(1L, "juanito",
                    "123", "jaja@gmail.com");

            contactoEmergenciaRepository.save(contact);

            for (int i = 0; i < 10; i++) {
                InfoPasajero passenger = InfoPasajero.builder()
                        .contactoEmergencia(contact)
                        .nacionalidad(UUID.randomUUID().toString())
                        .motivoViaje(UUID.randomUUID().toString())
                        .nacionalidad(UUID.randomUUID().toString())
                        .build();
                infoPasajeroRepository.save(passenger);
            }
        };
    }
}
