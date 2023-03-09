package br.com.isidrocorp.livraria.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.isidrocorp.livraria.model.Livro;

public interface LivroRepo extends CrudRepository<Livro, Integer>{
	
//	public ArrayList<Livro> recuperarTudo();
//	public Livro findByIdContaining(int id);
	public ArrayList<Livro> findByTituloContaining(String palavra);
//	public Livro adicionarNovo(Livro l);

}
