package com.example.adesao.services;

import org.springframework.stereotype.Service;

import com.example.adesao.dtos.ClienteDTO;
import com.example.adesao.exceptions.CpfJaExisteException;
import com.example.adesao.exceptions.CpfNaoEncontradoException;
import com.example.adesao.model.Cliente;

@Service
public interface ClienteService {

	Cliente cadastrar(Cliente cliente) throws CpfJaExisteException;

	Cliente atualizar(String cpf, ClienteDTO objDTO) throws CpfNaoEncontradoException;

	void remover(String cpfCliente) throws CpfNaoEncontradoException;

	Cliente buscarPorCPF(String cpfCliente) throws CpfNaoEncontradoException;

	

}
