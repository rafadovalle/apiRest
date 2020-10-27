package com.example.guiaBolso_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.guiaBolso_api.domain.Transacoes;

@Repository
public interface TransacoesRepository extends CrudRepository<Transacoes, Integer> {
	Transacoes findById(int id);
}
