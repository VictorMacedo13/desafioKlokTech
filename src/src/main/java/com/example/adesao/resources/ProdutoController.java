package com.example.adesao.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.adesao.dtos.ProdutoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Produto;
import com.example.adesao.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/produtos")
@Api(value = "Adesao API REST")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna um unico Produto")
	public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Integer id){
		Produto obj = null;
		try {
			obj = service.buscarPorId(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}
	

	@PostMapping
	@ApiOperation(value = "Cria um novo Profuto")
	public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoDTO objDTO){
		Produto novoObj = objDTO.cadastrar();
		
		try {
			service.cadastrar(novoObj);
		} catch (IdJaExisteException e) {
			e.printStackTrace();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{id}")
	@ApiOperation(value = "Remove um Produto")
	public void remover(@PathVariable(value="id") Integer idProduto) {
		try {
			service.remover(idProduto);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza as informacoes de um Produto")
	public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Integer id, @RequestBody ProdutoDTO objDTO){
		Produto obj = null;
		try {
			obj = service.atualizar(id, objDTO);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new ProdutoDTO(obj));
	}

}
