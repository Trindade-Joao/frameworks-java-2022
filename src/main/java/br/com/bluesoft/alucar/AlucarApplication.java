package br.com.bluesoft.alucar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class AlucarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlucarApplication.class, args);
	}
}
