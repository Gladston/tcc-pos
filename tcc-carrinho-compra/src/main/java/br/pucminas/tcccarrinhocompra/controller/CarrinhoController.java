package br.pucminas.tcccarrinhocompra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.tcccarrinhocompra.cliente.OrdemCompra;
import br.pucminas.tcccarrinhocompra.cliente.OrdemCompraService;
import br.pucminas.tcccarrinhocompra.dominio.Carrinho;
import br.pucminas.tcccarrinhocompra.dominio.Produto;
import br.pucminas.tcccarrinhocompra.repositorio.CarrinhoRepository;
import br.pucminas.tcccarrinhocompra.repositorio.ProdutoRepository;

@RestController
@RequestMapping("carrinho")
public class CarrinhoController {

	@Autowired private CarrinhoRepository carrinhoRepository;	
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private OrdemCompraService ordemService;

	@RequestMapping("/teste")
    public String teste() {
        return "Hello from EurekaClient!";
    }
	
	@RequestMapping(value="/{id}/ordem_compra", method=RequestMethod.POST)
	@ResponseBody
	public String criaOrdemCompra(@PathVariable("id") String id, @RequestBody OrdemCompra ordemCompra) {
		Carrinho carrinho = this.carrinhoRepository.findById(id);
		return this.ordemService.criaOrdemCompra(carrinho, ordemCompra.getCartaoCredito());
	}
	
	@RequestMapping("/{id}")
	@ResponseBody
	public Carrinho lista(@PathVariable("id") String id) {
		return this.carrinhoRepository.buscaCarrinhoEmAberto(id);
	}
	
	@RequestMapping(value = "/{id}/produto/{idProduto}", method = RequestMethod.DELETE) 
	public void excluiProduto(@PathVariable("id") String id, @PathVariable("idProduto") int idProduto) {
		
		Produto encontrado = this.produtoRepository.buscaProduto(id, idProduto);
		this.produtoRepository.delete(encontrado);
	}
	
	@RequestMapping(value = "/{id}/produto/{idProduto}", method = RequestMethod.PUT) 
	public void atualizaQuantidadeProduto(@PathVariable("id") String id, @PathVariable("idProduto") int idProduto, 
			@RequestBody Produto produto) {
		
		Produto encontrado = this.produtoRepository.buscaProduto(id, idProduto);
		encontrado.setQuantidade(produto.getQuantidade());
		
		this.produtoRepository.save(encontrado);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) 
	public void adicionaNoCarrinho(@PathVariable("id") String id, @RequestBody Produto produto) {
		Carrinho carrinho = this.carrinhoRepository.findById(id);
		produto.setCarrinho(carrinho);
		carrinho.addProduto(produto);

		this.carrinhoRepository.save(carrinho);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST) 
	@ResponseBody
	public Carrinho criaCarrinho(@PathVariable("id") String id, @RequestBody Produto produto) {
		Carrinho carrinho = new Carrinho(id);
		produto.setCarrinho(carrinho);
		carrinho.addProduto(produto);
		
		Carrinho novo = this.carrinhoRepository.save(carrinho);
		
		return novo;
	}
}
