package com.example.adesao.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adesao.dtos.ClienteDTO;
import com.example.adesao.exceptions.CpfJaExisteException;
import com.example.adesao.exceptions.CpfNaoEncontradoException;
import com.example.adesao.model.Cliente;
import com.example.adesao.repositories.ClienteRepository;
import com.example.adesao.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	ClienteRepository repository;
	
	@Override
	public Cliente cadastrar(Cliente cliente) throws CpfJaExisteException {
		Optional<Cliente> clienteExistente = repository.findById(cliente.getCpf());
		if(clienteExistente.isPresent()) {
			throw new CpfJaExisteException();
		}
		return repository.save(cliente);
	}

	@Override
	public Cliente atualizar(String cpfCliente, ClienteDTO clienteDTO) throws CpfNaoEncontradoException {
		Cliente cliente = buscarPorCPF(cpfCliente);
		cliente = clienteDTO.atualizarCliente(cliente);
		return repository.save(cliente);
	}

	@Override
	public void remover(String cpfCliente) throws CpfNaoEncontradoException {
		Cliente cliente = buscarPorCPF(cpfCliente);
		repository.delete(cliente);
	}

	@Override
	public Cliente buscarPorCPF(String cpfCliente) throws CpfNaoEncontradoException {
		Optional<Cliente> clienteExistente = repository.findById(cpfCliente);
		if(!clienteExistente.isPresent()) {
			throw new CpfNaoEncontradoException();
		}
		return clienteExistente.get();
	}

}
