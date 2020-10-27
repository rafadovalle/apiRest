package com.example.guiaBolso_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.guiaBolso_api.service.TransacoesService;

@SpringBootApplication
public class GuiaBolsoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuiaBolsoApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(TransacoesService transacoesService){
	    return args -> {
	    	transacoesService.lista();
	    };
	}
}
