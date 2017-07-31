package br.pucminas.tcccarrinhocompra.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name="ordem_compra")
public class OrdemCompra implements Serializable {

	private static final long serialVersionUID = 6483410314896273654L;

	@Id
	@Basic(optional = false)
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "cartao_credito")
	private String cartaoCredito;
	
	@OneToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name = "carrinho_compra_id", referencedColumnName = "id")
	private Carrinho carrinho;
	
	public OrdemCompra() {

	}
	
	public OrdemCompra(String cataoCredito) {
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

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

}
