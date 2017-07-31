package br.pucminas.tccordemcompra.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.tccordemcompra.dominio.OrdemCompra;

@Repository
public interface OrdemCompraRepository extends JpaRepository<OrdemCompra, Integer> {

	@Query("select o from OrdemCompra o where o.status = 1")
	public List<OrdemCompra> listaTodosPendentes();
	
	@Query("select o from OrdemCompra o")
	public List<OrdemCompra> listaTodos();

	@Query(value="select c from OrdemCompra c where c.id = :id")
	public OrdemCompra findById(@Param("id") int id);
	
}
