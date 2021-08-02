package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Launches the Spring application. Unmodified from starter code.
 */
@SpringBootApplication
public class CritterApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(CritterApplication.class, args);
	}

}
