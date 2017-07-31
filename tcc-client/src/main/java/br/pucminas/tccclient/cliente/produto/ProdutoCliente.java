package br.pucminas.tccclient.cliente.produto;

import org.springframework.web.bind.annotation.RequestMapping;

public interface ProdutoCliente {

	@RequestMapping("/produtos/")
    String listaProdutos();
}
