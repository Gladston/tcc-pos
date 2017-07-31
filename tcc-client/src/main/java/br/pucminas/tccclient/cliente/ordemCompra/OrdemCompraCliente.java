package br.pucminas.tccclient.cliente.ordemCompra;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.pucminas.tccclient.cliente.carrinho.OrdemCompra;

public interface OrdemCompraCliente {

	@RequestMapping("/ordens_compra/")
	@ResponseBody
	public String list();
	
	@RequestMapping(value ="/ordens_compra/{id}", method = RequestMethod.PATCH)
	public void atualizaCartaoCreditoOrdemCompra(@PathVariable("id") int id, @RequestBody OrdemCompra ordemCompra);
}
