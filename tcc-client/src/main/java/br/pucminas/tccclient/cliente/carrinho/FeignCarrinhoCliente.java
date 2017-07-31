package br.pucminas.tccclient.cliente.carrinho;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(
		name="carrinho-compra-microservice",
		fallback = FeignCarrinhoCliente.CarrinhoClientFallback.class
)
public interface FeignCarrinhoCliente extends CarrinhoCliente {

	@Component
    public static class CarrinhoClientFallback {


    }
}
