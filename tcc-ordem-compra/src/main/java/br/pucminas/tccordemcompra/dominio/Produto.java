package br.pucminas.tccordemcompra.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name="produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = -9172104315890391227L;

	@Id
	@Basic(optional = false)
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Basic(optional = false)
	@Column(name = "nome")
	private String nome;
	
	@Basic(optional = false)
	@Column(name = "codigo")
	private String codigo;
	
	@Basic(optional = false)
	@Column(name = "quantidade")
	private int quantidade;
	
	@Basic(optional = false)
	@Column(name = "preco")
	private float preco;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name = "ordem_compra_id", referencedColumnName = "id")
	private OrdemCompra ordem;
	
	
	public Produto() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@JsonIgnore
	public OrdemCompra getOrdem() {
		return ordem;
	}

	public void setOrdem(OrdemCompra ordem) {
		this.ordem = ordem;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
}
