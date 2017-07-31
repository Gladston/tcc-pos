package br.pucminas.tccclient.cliente.produto;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(
		name="produto-microservice",
		fallback = FeignProdutoCliente.ProdutoClientFallback.class
)
public interface FeignProdutoCliente extends ProdutoCliente {

	@Component
    public static class ProdutoClientFallback implements ProdutoCliente {

		@Override
		public String listaProdutos() {
			return ProdutoService.getProdutos().toString();
		}
    }
}
