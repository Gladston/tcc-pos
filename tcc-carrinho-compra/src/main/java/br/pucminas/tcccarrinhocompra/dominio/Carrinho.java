package br.pucminas.tcccarrinhocompra.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name="carrinho_compra")
public class Carrinho implements Serializable {

	private static final long serialVersionUID = -8997421405456177314L;

	@Id
	@Basic(optional = false)
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "session_id")
	private String sessionId;
	
	@Basic(optional = false)
	@Column(name = "data_criacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	
	@Basic(optional = false)
	@Column(name = "status")
	private boolean status;
	
	@OneToMany(mappedBy = "carrinho", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Set<Produto> produtos;
	
	@OneToOne(mappedBy="carrinho", optional=false, fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private OrdemCompra ordem;
	
	public Carrinho() {
		this.status = true;
		this.dataCriacao = Calendar.getInstance();
	}
	
	public Carrinho(String sessionId) {
		this.sessionId = sessionId;
		this.status = true;
		this.dataCriacao = Calendar.getInstance();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void addProduto(Produto produto) {
		if(produtos == null) {
			produtos = new HashSet<>();
		}
		
		this.produtos.forEach(item -> {
			if(item.getCodigo() == produto.getCodigo()) {
				item.setQuantidade(item.getQuantidade() + 1);
			} else {
				this.produtos.add(produto);
			}
		});
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public OrdemCompra getOrdem() {
		return ordem;
	}

	public void setOrdem(OrdemCompra ordem) {
		this.ordem = ordem;
	}
}
