package com.example.adesao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.adesao.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,String>{

}
