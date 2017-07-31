package br.pucminas.tccordemcompra;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.pucminas.tccordemcompra.dominio.OrdemCompra;
import br.pucminas.tccordemcompra.dominio.Status;
import br.pucminas.tccordemcompra.repositorio.OrdemCompraRepository;

@Component
public class RotinaOrdemCompra {
	
	@Autowired private OrdemCompraRepository repository;

	@Scheduled(fixedRate = 30000)
    public void verificaCartaoCredito() {
		List<OrdemCompra> ordens = this.repository.listaTodosPendentes();
		
		List<OrdemCompra> recusados = new ArrayList<>();
		List<OrdemCompra> aceitos = new ArrayList<>();
		for (OrdemCompra ordemCompra : ordens) {
			try {
				boolean valido = CCUtils.validCC(ordemCompra.getCartaoCredito());
				
				if(valido) {
					ordemCompra.setStatus(Status.FINALIZADO.getId());
					aceitos.add(ordemCompra);
				} else {
					ordemCompra.setStatus(Status.RECUSADO.getId());
					recusados.add(ordemCompra);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.repository.save(aceitos);
		this.repository.save(recusados);
    }
}
