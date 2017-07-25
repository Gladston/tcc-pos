package br.pucminas.tccproduto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import br.pucminas.tccproduto.controller.ProdutoController;

@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan("br.pucminas.tccproduto.controller")
public class TccProdutoApplication {


	public static void main(String[] args) {
		SpringApplication.run(TccProdutoApplication.class, args);
	}
}
