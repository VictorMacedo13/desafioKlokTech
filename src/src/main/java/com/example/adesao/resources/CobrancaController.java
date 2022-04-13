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

import com.example.adesao.dtos.CobrancaDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Cobranca;
import com.example.adesao.services.CobrancaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cobrancas")
@Api(value = "Adesao API REST")
@CrossOrigin(origins = "*")
public class CobrancaController {

	@Autowired
	CobrancaService service;
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna uma unica Cobranca")
	public ResponseEntity<CobrancaDTO> buscarPorId(@PathVariable Integer id){
		Cobranca obj = null;
		try {
			obj = service.buscarPorId(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new CobrancaDTO(obj));
	}
	

	@PostMapping
	@ApiOperation(value = "Cria uma nova Cobranca")
	public ResponseEntity<CobrancaDTO> cadastrar(@RequestBody CobrancaDTO objDTO){
		Cobranca novoObj = objDTO.cadastrar();
		
		try {
			service.cadastrar(novoObj);
		} catch (IdJaExisteException e) {
			e.printStackTrace();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{id}")
	@ApiOperation(value = "Remove uma Cobranca")
	public void remover(@PathVariable(value="id") Integer idCobranca) {
		try {
			service.remover(idCobranca);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza as informacoes de uma Cobranca")
	public ResponseEntity<CobrancaDTO> atualizar(@PathVariable Integer id, @RequestBody CobrancaDTO objDTO){
		Cobranca obj = null;
		try {
			obj = service.atualizar(id, objDTO);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new CobrancaDTO(obj));
	}
}
