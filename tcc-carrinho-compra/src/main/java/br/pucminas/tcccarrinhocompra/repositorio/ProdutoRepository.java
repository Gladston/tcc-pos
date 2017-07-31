package br.pucminas.tcccarrinhocompra.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.tcccarrinhocompra.dominio.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("select p from Produto p join p.carrinho c where p.id = :idProduto and c.sessionId = :sessionId and c.status = 1")
	public Produto buscaProduto(@Param("sessionId") String idCarrinho, @Param("idProduto") int idProduto);
}
