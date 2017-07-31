package br.pucminas.tccclient.cliente.carrinho;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CarrinhoCliente {

	@RequestMapping("/carrinho/{id}")
    String buscaCarrinho(@PathVariable("id") String id);
	
	@RequestMapping(value = "/{id}/produto/{idProduto}", method = RequestMethod.DELETE) 
	void excluiProduto(@PathVariable("id") String id, @PathVariable("idProduto") int idProduto);
	
	@RequestMapping(value = "/carrinho/{id}/produto/{idProduto}", method = RequestMethod.PUT) 
	void atualizaQuantidadeProduto(@PathVariable("id") String id, @PathVariable("idProduto") int idProduto, 
			@RequestBody Produto produto);
	
	@RequestMapping(value = "/carrinho/{id}", method = RequestMethod.PUT) 
	void adicionaNoCarrinho(@PathVariable("id") String id, @RequestBody Produto produto);
	
	@RequestMapping(value = "/carrinho/{id}", method = RequestMethod.POST) 
	@ResponseBody
	String criaCarrinho(@PathVariable("id") String id, @RequestBody Produto produto);
	
	@RequestMapping(value="/carrinho/{id}/ordem_compra", method=RequestMethod.POST)
	@ResponseBody
	public String criaOrdemCompra(@PathVariable("id") String id, @RequestBody OrdemCompra ordemCompra);
}
