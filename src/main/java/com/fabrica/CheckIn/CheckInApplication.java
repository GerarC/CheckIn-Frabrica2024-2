package com.fabrica.CheckIn;

import com.fabrica.CheckIn.model.ContactoEmergencia;
import com.fabrica.CheckIn.model.InfoPasajero;
import com.fabrica.CheckIn.repository.ContactoEmergenciaRepository;
import com.fabrica.CheckIn.repository.InfoPasajeroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class CheckInApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckInApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ContactoEmergenciaRepository contactoEmergenciaRepository,
										InfoPasajeroRepository InfoPasajeroRepository){

		return args -> {
			ContactoEmergencia contato=new ContactoEmergencia(1L,"juanito",
					"123", "jaja@gmail.com");

			contactoEmergenciaRepository.save(contato);

			for(int i = 0;i < 10; i++){
				InfoPasajero pasajero = InfoPasajero.builder()
						.ContactoEmergencia(contato)
						.nacionalidad(UUID.randomUUID().toString())
						.motivoViaje(UUID.randomUUID().toString())
						.nacionalidad(UUID.randomUUID().toString())
						.build();
				InfoPasajeroRepository.save(pasajero);
			}
		};

	}

}
