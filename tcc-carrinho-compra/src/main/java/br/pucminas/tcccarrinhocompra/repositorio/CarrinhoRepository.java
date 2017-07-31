package br.pucminas.tcccarrinhocompra.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.tcccarrinhocompra.dominio.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

	@Query("select o from Carrinho o where o.status = 1 and o.sessionId = :id")
	public Carrinho buscaCarrinhoEmAberto(@Param("id") String id);

	@Query(value="select c from Carrinho c where c.sessionId = :id and c.status = 1")
	public Carrinho findById(@Param("id") String id);
}
