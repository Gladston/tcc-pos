package br.pucminas.tccclient.cliente.carrinho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;;

@Component
public class RotinaOrdemCompra {
	
	@Autowired private CarrinhoCompraService carrinhoService;

	@Scheduled(fixedRate = 30000)
    public void verificaCartaoCredito() {
		carrinhoService.criaOrdemCompra();	
    }
}
