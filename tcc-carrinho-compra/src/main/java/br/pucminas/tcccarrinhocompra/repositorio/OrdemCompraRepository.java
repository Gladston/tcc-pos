package br.pucminas.tcccarrinhocompra.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.tcccarrinhocompra.dominio.OrdemCompra;

@Repository
public interface OrdemCompraRepository extends JpaRepository<OrdemCompra, Integer> {

	@Query("select o from OrdemCompra o")
	public List<OrdemCompra> listaOrdens();
	
	@Modifying
	@Query(value="delete from ordem_compra where id = :id", nativeQuery=true)
	public void excluiOrdemCompra(@Param("id") int ordem);
}
