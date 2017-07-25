package br.pucminas.tccproduto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.pucminas.tccproduto.dominio.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

	public Produto findByCodigo(String codigo);
}
