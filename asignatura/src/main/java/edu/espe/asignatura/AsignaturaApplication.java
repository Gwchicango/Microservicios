package edu.espe.asignatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AsignaturaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AsignaturaApplication.class, args);

		System.out.println("Hola Mundo");
	}


}
