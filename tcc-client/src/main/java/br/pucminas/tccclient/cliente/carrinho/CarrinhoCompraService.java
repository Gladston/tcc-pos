package br.pucminas.tccclient.cliente.carrinho;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CarrinhoCompraService {

	@Autowired
    private FeignCarrinhoCliente carrinhoCliente;
	
	private static Map<String, OrdemCompra> ordensCompra = new HashMap<>();
	
    public String buscaCarrinho(String id) {
    	return carrinhoCliente.buscaCarrinho(id);
    }
	
    public void excluiProduto(String id, int idProduto) {
    	carrinhoCliente.excluiProduto(id, idProduto);
    }
	
    public void atualizaQuantidadeProduto(String id, int idProduto, Produto produto) {
    	carrinhoCliente.atualizaQuantidadeProduto(id, idProduto, produto);
    }
	
    public void adicionaNoCarrinho(String id, Produto produto) {
    	carrinhoCliente.adicionaNoCarrinho(id, produto);
    }
	
    public String criaCarrinho(String id, Produto produto) {
    	return carrinhoCliente.criaCarrinho(id, produto);
    }
    
    @HystrixCommand(fallbackMethod="armazenaOrdemCompra")
	public String criaOrdemCompra(String id, OrdemCompra ordemCompra) {
		return carrinhoCliente.criaOrdemCompra(id, ordemCompra);
	}
    
    public String armazenaOrdemCompra(String id, OrdemCompra ordemCompra) {
    	ordensCompra.put(id, ordemCompra);
    	
    	JSONObject json = new JSONObject();
    	try {
			json.put("cartaoCredito", ordemCompra.getCartaoCredito());
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	return json.toString();
    }

	public void criaOrdemCompra() {
		try {
			Set<String> ids = ordensCompra.keySet();
			
			for (String id : ids) {
				OrdemCompra ordemCompra = ordensCompra.get(id);
				carrinhoCliente.criaOrdemCompra(id, ordemCompra);
				ordensCompra.remove(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
