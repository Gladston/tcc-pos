package br.pucminas.tcccarrinhocompra.cliente;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(
		name="ordem-compra-microservice",
		fallback = FeignOrdemCompraCliente.OrdemClientFallback.class
)
public interface FeignOrdemCompraCliente extends OrdemCompraCliente {

	@Component
    public static class OrdemClientFallback {
		
    }
}
