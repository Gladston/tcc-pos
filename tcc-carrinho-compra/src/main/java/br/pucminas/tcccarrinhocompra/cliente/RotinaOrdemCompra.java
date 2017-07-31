package br.pucminas.tcccarrinhocompra.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.pucminas.tcccarrinhocompra.dominio.OrdemCompra;
import br.pucminas.tcccarrinhocompra.repositorio.OrdemCompraRepository;;

@Component
public class RotinaOrdemCompra {
	
	@Autowired private OrdemCompraRepository repository;
	@Autowired private OrdemCompraService ordemService;

	@Scheduled(fixedRate = 30000)
    public void verificaCartaoCredito() {
		List<OrdemCompra> ordens = this.repository.listaOrdens();
		
		for (OrdemCompra ordemCompra : ordens) {
			ordemService.criaOrdemCompra(ordemCompra);
		}		
    }
}
