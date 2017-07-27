package br.pucminas.tccproduto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.tccproduto.dominio.Produto;
import br.pucminas.tccproduto.repositorio.ProdutoRepository;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
//	@Autowired
//	private ProdutoRepository repository;

	@RequestMapping("/teste")
    public String teste() {
        return "Hello from EurekaClient!";
    }
	
//	@RequestMapping("/")
//	public List<Produto> list() {
//		return this.repository.findAll();
//	}
}
