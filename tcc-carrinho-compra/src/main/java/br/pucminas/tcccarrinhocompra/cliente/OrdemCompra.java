package br.pucminas.tcccarrinhocompra.cliente;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrdemCompra implements Serializable {

	private static final long serialVersionUID = 6483410314896273654L;

	private Integer id;
	private String cartaoCredito;
	private Set<Produto> produtos;
	
	public OrdemCompra() {

	}
	
	public OrdemCompra(String cataoCredito, Set<Produto> produtos) {
		this.produtos= produtos;
		this.cartaoCredito = cataoCredito;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(String cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
