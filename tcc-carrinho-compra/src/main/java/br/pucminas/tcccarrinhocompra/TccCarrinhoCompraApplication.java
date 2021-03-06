package br.pucminas.tcccarrinhocompra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableScheduling
@ComponentScan({ "br.pucminas.tcccarrinhocompra.controller", "br.pucminas.tcccarrinhocompra.cliente" })
public class TccCarrinhoCompraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TccCarrinhoCompraApplication.class, args);
	}
}
