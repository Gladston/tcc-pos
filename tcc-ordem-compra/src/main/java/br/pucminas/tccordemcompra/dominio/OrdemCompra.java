package br.pucminas.tccordemcompra.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Basic(optional = false)
	@Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	
	@Basic(optional = false)
	@Column(name = "status")
	private int status;
	
	@OneToMany(mappedBy = "ordem", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Set<Produto> produtos;
	
	public OrdemCompra() {
		this.dataCriacao = Calendar.getInstance();
		this.status = Status.PENDENTE.getId();
	}
	
	public OrdemCompra(String cataoCredito, Set<Produto> produtos) {
		this.dataCriacao = Calendar.getInstance();
		this.status = Status.PENDENTE.getId();
		this.produtos= produtos;
		this.cartaoCredito = cataoCredito;
	}
	
	public float getValorTotal() {
		float valor = 0;
		
		if(produtos != null) {
			for (Produto produto : produtos) {
				valor += produto.getPreco();
			}
		}
		
		return valor;
	}
	
	public int getTotalProdutos() {
		int qtd = 0;
		
		if(produtos != null) {
			for (Produto produto : produtos) {
				qtd += produto.getQuantidade();
			}
		}
		
		return qtd;
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

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getStatus() {
		return Status.valueOfId(status).toString();
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
