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

import com.example.adesao.dtos.PagamentoDTO;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Pagamento;
import com.example.adesao.services.PagamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pagamentos")
@Api(value = "Adesao API REST")
@CrossOrigin(origins = "*")
public class PagamentoController {

	@Autowired
	PagamentoService service;
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna um unico Pagamento")
	public ResponseEntity<PagamentoDTO> buscarPorId(@PathVariable Integer id){
		Pagamento obj = null;
		try {
			obj = service.buscarPorId(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new PagamentoDTO(obj));
	}
	

	@PostMapping
	@ApiOperation(value = "Cria um novo pagamento")
	public ResponseEntity<PagamentoDTO> cadastrar(@RequestBody PagamentoDTO objDTO){
		Pagamento novoObj = objDTO.cadastrar();
		
		try {
			service.cadastrar(novoObj);
		} catch (IdJaExisteException e) {
			e.printStackTrace();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{id}")
	@ApiOperation(value = "Remove um Pagamento")
	public void remover(@PathVariable(value="id") Integer idPagamento) {
		try {
			service.remover(idPagamento);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza as informacoes de um Pagamento")
	public ResponseEntity<PagamentoDTO> atualizar(@PathVariable Integer id, @RequestBody PagamentoDTO objDTO){
		Pagamento obj = null;
		try {
			obj = service.atualizar(id, objDTO);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new PagamentoDTO(obj));
	}
}
