package com.lta.systempayments;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lta.systempayments.entities.Estudiante;
import com.lta.systempayments.entities.Pago;
import com.lta.systempayments.enums.PagoStatus;
import com.lta.systempayments.enums.TypePago;
import com.lta.systempayments.repository.EstudianteRepository;
import com.lta.systempayments.repository.PagoRepository;

@SpringBootApplication
public class SistemaPagosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPagosBackendApplication.class, args);
	}

	/* Esta interfaz ejecuta algo después de que la aplicación haya iniciado */
	@Bean
	CommandLineRunner CommandLineRunner(EstudianteRepository estudianteRepository, PagoRepository pagoRepository){
		return args -> {
			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Miguel")
					.apellido("Hernández")
					.codigo("152131")
					.programaId("LTA1")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Óscar")
					.apellido("Martín")
					.codigo("12345")
					.programaId("LTA1")
					.build());

			estudianteRepository.save(Estudiante.builder()
					.id(UUID.randomUUID().toString())
					.nombre("Juan")
					.apellido("Pina")
					.codigo("54321")
					.programaId("LTA2")
					.build());

			TypePago tiposPago[] = TypePago.values();
			Random random = new Random();

			estudianteRepository.findAll().forEach(estudiante -> {
				for (int i = 0; i < 10; i++) {
					int index = random.nextInt(tiposPago.length);
					Pago pago = Pago.builder()
							.cantidad(1000+(int)(Math.random()*20000))
							.type(tiposPago[index])
							.status (PagoStatus.CREADO)
							.fecha (LocalDate.now())
							.estudiante(estudiante)
							.build();
					pagoRepository.save(pago);
				}
			});
		};
	}


}
