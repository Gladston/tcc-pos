package br.pucminas.tccclient.cliente.ordemCompra;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(
		name="ordem-compra-microservice",
		fallback = FeignOrdemCompraCliente.OrdemCompraClientFallback.class
)
public interface FeignOrdemCompraCliente extends OrdemCompraCliente {

	@Component
    public static class OrdemCompraClientFallback {


    }
}
