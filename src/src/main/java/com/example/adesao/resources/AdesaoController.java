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

import com.example.adesao.dtos.AdesaoDTO;
import com.example.adesao.exceptions.CpfNaoEncontradoException;
import com.example.adesao.exceptions.IdJaExisteException;
import com.example.adesao.exceptions.IdNaoEncontradoException;
import com.example.adesao.model.Adesao;
import com.example.adesao.model.Cliente;
import com.example.adesao.model.Produto;
import com.example.adesao.services.AdesaoService;
import com.example.adesao.services.ClienteService;
import com.example.adesao.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/adesoes")
@Api(value = "Adesao API REST")
@CrossOrigin(origins = "*")
public class AdesaoController {
	
	@Autowired
	AdesaoService service;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ProdutoService produtoService;
	
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Retorna uma unica Adesao")
	public ResponseEntity<AdesaoDTO> buscarPorId(@PathVariable Integer id){
		Adesao obj = null;
		try {
			obj = service.buscarPorId(id);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new AdesaoDTO(obj));
	}
	

	@PostMapping
	@ApiOperation(value = "Cria uma nova Adesao")
	public ResponseEntity<AdesaoDTO> cadastrar(@RequestBody AdesaoDTO objDTO){
		Adesao novoObj = objDTO.cadastrar();
		try {
			Produto produto = produtoService.buscarPorId(objDTO.getIdProduto());
			Cliente cliente = clienteService.buscarPorCPF(objDTO.getIdCliente());
			novoObj.setCliente(cliente);
			novoObj.setProduto(produto);
		} catch (IdNaoEncontradoException | CpfNaoEncontradoException e1) {
			e1.printStackTrace();
		}
		
		try {
			service.cadastrar(novoObj);
		} catch (IdJaExisteException e) {
			e.printStackTrace();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{id}")
	@ApiOperation(value = "Cancela uma Adesao")
	public void cancelar(@PathVariable(value="id")Integer idAdesao) {
		try {
			service.cancelar(idAdesao);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Atualiza as informacoes de uma Adesao")
	public ResponseEntity<AdesaoDTO> atualizar(@PathVariable Integer id, @RequestBody AdesaoDTO objDTO){
		Adesao obj = null;
		try {
			obj = service.atualizar(id, objDTO);
		} catch (IdNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new AdesaoDTO(obj));
	}
	
	
}
