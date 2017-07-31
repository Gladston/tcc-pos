package br.pucminas.tccordemcompra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.tccordemcompra.dominio.OrdemCompra;
import br.pucminas.tccordemcompra.dominio.Status;
import br.pucminas.tccordemcompra.repositorio.OrdemCompraRepository;

@RestController
@RequestMapping("ordens_compra")
public class OrdemCompraController {
	
	@Autowired private OrdemCompraRepository repository;

	@RequestMapping("/")
	@ResponseBody
	public List<OrdemCompra> list() {
		return this.repository.listaTodos();
	}
	
	@RequestMapping(value ="/", method = RequestMethod.POST)
	public OrdemCompra criaOrdemCompra(@RequestBody  OrdemCompra ordemCompra) {
		ordemCompra.getProdutos().forEach(item -> item.setOrdem(ordemCompra));
		OrdemCompra nova = this.repository.save(ordemCompra);
		return nova;
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PATCH)
	public void atualizaCartaoCreditoOrdemCompra(@PathVariable("id") int id, @RequestBody OrdemCompra ordemCompra) {
		OrdemCompra ordem = this.repository.findById(id);
		ordem.setCartaoCredito(ordemCompra.getCartaoCredito());
		ordem.setStatus(Status.PENDENTE.getId());
		
		this.repository.save(ordem);
	}
}
