package br.com.isidrocorp.livraria.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.livraria.dto.MensagemErro;
import br.com.isidrocorp.livraria.model.Livro;
import br.com.isidrocorp.livraria.repo.LivroRepo;

@RestController
public class LivroController {
	
	@Autowired
	private LivroRepo repo;
	
	@GetMapping("/livros")
	public ArrayList<Livro> recuperarTudo(){
		return (ArrayList<Livro>)repo.findAll();
	}
	
	@GetMapping("/livros/{id}")
	public ResponseEntity<Livro> findByIdContaining(@PathVariable int id){
		Livro l = repo.findById(id).orElse(null);
		if(l != null) {
			return ResponseEntity.ok(l);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/livros/busca")
	public ResponseEntity<?> findByTituloContaining(@RequestParam(name = "palavra") String palavra){
		ArrayList<Livro> lista = repo.findByTituloContaining(palavra);
		if (lista.size() > 0) {
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.status(404).body(new MensagemErro(9876,"Criterio de busca nao foi satisfeito"));
	}
	
	@PostMapping("/livros")
	public ResponseEntity<Livro> adicionarNovo(@RequestBody Livro l){
		Livro res = repo.save(l);
		if (res != null) {
			return ResponseEntity.status(201).body(res);
		}
		return ResponseEntity.badRequest().build();
	}
	

}
