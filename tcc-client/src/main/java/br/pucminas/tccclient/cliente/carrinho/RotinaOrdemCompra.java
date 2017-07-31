package br.pucminas.tccclient.cliente.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.pucminas.tccclient.cliente.ordemCompra.OrdemCompraService;;

@Component
public class RotinaOrdemCompra {
	
	@Autowired private CarrinhoCompraService carrinhoService;
	@Autowired private OrdemCompraService ordemService;

	@Scheduled(fixedRate = 30000)
    public void verificaCartaoCredito() {
		carrinhoService.criaOrdemCompra();	
    }
	
	@Scheduled(fixedRate = 30000)
	public void atualizaOrdensAtualizacaoCartao() {
		ordemService.atualizaOrdemCompra();
	}
}
