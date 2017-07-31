package br.pucminas.tcccarrinhocompra.cliente;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.pucminas.tcccarrinhocompra.dominio.Carrinho;
import br.pucminas.tcccarrinhocompra.repositorio.OrdemCompraRepository;

@Service
public class OrdemCompraService {

	@Autowired private FeignOrdemCompraCliente ordemCliente;
	@Autowired private OrdemCompraRepository ordemRepository;

	@Transactional
	public void criaOrdemCompra(br.pucminas.tcccarrinhocompra.dominio.OrdemCompra ordemCompra) {
		Set<Produto> produtos = new HashSet<>();
		ordemCompra.getCarrinho().getProdutos().forEach(item -> {
			produtos.add(new Produto(item.getNome(), item.getCodigo(), item.getQuantidade(), item.getPreco()));
		});
		
		br.pucminas.tcccarrinhocompra.cliente.OrdemCompra ordem = 
				new br.pucminas.tcccarrinhocompra.cliente.OrdemCompra(ordemCompra.getCartaoCredito(), produtos);
		
		ordemCliente.criaOrdemCompra(ordem);
		ordemRepository.excluiOrdemCompra(ordemCompra.getId());
	}
	
	@HystrixCommand(fallbackMethod="armazenaOrdemCompra")
    public String criaOrdemCompra(Carrinho carrinho, String cartaoCredito) {
		OrdemCompra ordem = getOrdemCompraCliente(carrinho, cartaoCredito);
		
    	return ordemCliente.criaOrdemCompra(ordem);
    }

	private OrdemCompra getOrdemCompraCliente(Carrinho carrinho, String cartaoCredito) {
		Set<Produto> produtos = new HashSet<>();
		carrinho.getProdutos().forEach(item -> produtos.add(new Produto(item.getNome(), item.getCodigo(), item.getQuantidade(), item.getPreco())));
		
		OrdemCompra ordem = new OrdemCompra(cartaoCredito, produtos);
		return ordem;
	}
	
	public String armazenaOrdemCompra(Carrinho carrinho, String cartaoCredito) {
		br.pucminas.tcccarrinhocompra.dominio.OrdemCompra ordem = new br.pucminas.tcccarrinhocompra.dominio.OrdemCompra(cartaoCredito);
		ordem.setCarrinho(carrinho);
		carrinho.setOrdem(ordem);
		
		this.ordemRepository.save(ordem);
		
		OrdemCompra ordemClient = getOrdemCompraCliente(carrinho, cartaoCredito);
		return getJSONOrdemCliente(ordemClient);
	}
	
	private String getJSONOrdemCliente(OrdemCompra ordem) {
		JSONObject json = new JSONObject();
		
		try {
			json.put("cartaoCredito", ordem.getCartaoCredito());
			JSONArray produtos = new JSONArray();
			
			ordem.getProdutos().forEach(item -> {
				JSONObject prod = new JSONObject();
				try {
					prod.put("nome", item.getNome());
					prod.put("codigo", item.getCodigo());
					prod.put("quantidade", item.getQuantidade());
					prod.put("preco", item.getPreco());
					
					produtos.put(prod);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			});
			
			json.put("produtos", produtos);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json.toString();
	}
}
