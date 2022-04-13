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

import com.example.adesao.dtos.ClienteDTO;
import com.example.adesao.exceptions.CpfJaExisteException;
import com.example.adesao.exceptions.CpfNaoEncontradoException;
import com.example.adesao.model.Cliente;
import com.example.adesao.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(value = "Adesao API REST")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	ClienteService service;
	
	@GetMapping(value = "/{cpf}")
	@ApiOperation(value = "Retorna um Cliente a partir de um CPF")
	public ResponseEntity<ClienteDTO> buscarPorCpf(@PathVariable String cpf){
		Cliente obj = null;
		try {
			obj = service.buscarPorCPF(cpf);
		} catch (CpfNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	

	@PostMapping
	@ApiOperation(value = "Cria um novo Cliente")
	public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO objDTO){
		Cliente novoObj = objDTO.cadastrar();
		
		try {
			service.cadastrar(novoObj);
		} catch (CpfJaExisteException e) {
			e.printStackTrace();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(novoObj.getCpf()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/{cpf}")
	@ApiOperation(value = "Remove um Cliente")
	public void remover(@PathVariable(value="cpf") String cpfCliente) {
		try {
			service.remover(cpfCliente);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@PutMapping(value = "/{cpf}")
	@ApiOperation(value = "Atualiza as informacoes de um Cliente")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable String cpf, @RequestBody ClienteDTO objDTO){
		Cliente obj = null;
		try {
			obj = service.atualizar(cpf, objDTO);
		} catch (CpfNaoEncontradoException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
}
