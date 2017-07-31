package br.pucminas.tccclient.ordemCompra;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.pucminas.tccclient.cliente.carrinho.OrdemCompra;

@Service
public class OrdemCompraService {

	@Autowired private FeignOrdemCompraCliente carrinhoCliente;
	private static Map<Integer, OrdemCompra> ordensCompra = new HashMap<>();
	
	public String list() {
		return this.carrinhoCliente.list();
	}
	
	@HystrixCommand(fallbackMethod="armazenaAtualizacaoOrdemCompra")
	public void atualizaCartaoCreditoOrdemCompra(int id, OrdemCompra ordemCompra) {
		this.carrinhoCliente.atualizaCartaoCreditoOrdemCompra(id, ordemCompra);
	}
	
	public void armazenaAtualizacaoOrdemCompra(int id, OrdemCompra ordemCompra) {
		ordensCompra.put(id, ordemCompra);
	}
	
	public void atualizaOrdemCompra() {
		try {
			Set<Integer> ids = ordensCompra.keySet();
			
			for (Integer id : ids) {
				OrdemCompra ordemCompra = ordensCompra.get(id);
				carrinhoCliente.atualizaCartaoCreditoOrdemCompra(id, ordemCompra);
				ordensCompra.remove(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
