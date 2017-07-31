package br.pucminas.tccclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import br.pucminas.tccclient.cliente.carrinho.CarrinhoCompraService;
import br.pucminas.tccclient.cliente.carrinho.OrdemCompra;
import br.pucminas.tccclient.cliente.carrinho.Produto;
import br.pucminas.tccclient.cliente.produto.ProdutoService;

@RestController
@RequestMapping("loja")
public class LojaController {

	@Autowired private ProdutoService produtoCliente;
	@Autowired private CarrinhoCompraService carrinhoCliente;
	
	@RequestMapping("/produtos")
    public String listaProdutos() {
        return produtoCliente.listaProdutos();
    }
	
	@RequestMapping(value="/carrinho/ordem_compra", method=RequestMethod.POST)
	@ResponseBody
	public String criaOrdemCompra(@RequestBody OrdemCompra ordemCompra) {
		return this.carrinhoCliente.criaOrdemCompra(getSessionId(), ordemCompra);
	}
	
	@RequestMapping("/carrinho/")
	public String buscaCarrinho() {
    	return carrinhoCliente.buscaCarrinho(getSessionId());
    }
	
	@RequestMapping(value = "/carrinho/produto/{idProduto}", method = RequestMethod.DELETE) 
    public void excluiProduto(@PathVariable("idProduto") int idProduto) {
    	carrinhoCliente.excluiProduto(getSessionId(), idProduto);
    }
	
	@RequestMapping(value = "/carrinho/produto/{idProduto}", method = RequestMethod.PUT) 
    public void atualizaQuantidadeProduto(@PathVariable("idProduto") int idProduto, @RequestBody Produto produto) {
    	carrinhoCliente.atualizaQuantidadeProduto(getSessionId(), idProduto, produto);
    }
	
	@RequestMapping(value = "/carrinho/", method = RequestMethod.PUT) 
    public void adicionaNoCarrinho(@RequestBody Produto produto) {
    	carrinhoCliente.adicionaNoCarrinho(getSessionId(), produto);
    }
	
	@RequestMapping(value = "/carrinho/", method = RequestMethod.POST) 
	@ResponseBody
    public String criaCarrinho(@RequestBody Produto produto) {
    	return carrinhoCliente.criaCarrinho(getSessionId(), produto);
    }
	
	private String getSessionId() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}
}
