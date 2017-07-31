package br.pucminas.tccclient.cliente.produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProdutoService {

	@Autowired
    private FeignProdutoCliente produtoCliente;
	
	@HystrixCommand(fallbackMethod="listaProdutosNovamente")
	public String listaProdutos() {
        return produtoCliente.listaProdutos();
    }
	
	public String listaProdutosNovamente() {
		return ProdutoService.getProdutos().toString();
	}
	
	public static JSONArray getProdutos() {
		JSONArray produtos = new JSONArray();
		
		JSONObject produto1 = new JSONObject();
		JSONObject produto2 = new JSONObject();
		
		try {
			produto1.put("nome", "Relógio Automático");
			produto1.put("quantidade", 30);
			produto1.put("preco", 220);
			produto1.put("descricao", "Relógio Automático TCC prata");
			produto1.put("codigo", "bcd564");
			produto1.put("foto", "relogio_automatico.png");
			
			produto2.put("nome", "TV 15 polegadas");
			produto2.put("quantidade", 20);
			produto2.put("preco", 1110);
			produto2.put("descricao", "TV 15 polegadas TCC 4K");
			produto2.put("codigo", "qwe123");
			produto2.put("foto", "tv_tcc.png");
			
			produtos.put(produto1);
			produtos.put(produto2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return produtos;
	}
}
