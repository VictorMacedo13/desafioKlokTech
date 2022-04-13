package com.example.adesao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.adesao.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}
