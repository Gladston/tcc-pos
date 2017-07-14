package br.pucminas.tccdescobertaservico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TccDescobertaServicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccDescobertaServicoApplication.class, args);
	}
}
