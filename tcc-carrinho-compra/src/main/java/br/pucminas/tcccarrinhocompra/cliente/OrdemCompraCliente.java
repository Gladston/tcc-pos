package br.pucminas.tcccarrinhocompra.cliente;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface OrdemCompraCliente {

	@RequestMapping(value="/ordens_compra/", method = RequestMethod.POST)
	String criaOrdemCompra(@RequestBody OrdemCompra ordem);
}
