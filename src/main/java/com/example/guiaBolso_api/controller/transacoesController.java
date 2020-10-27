package com.example.guiaBolso_api.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.guiaBolso_api.domain.Transacoes;
import com.example.guiaBolso_api.domain.TransacoesExibe;
import com.example.guiaBolso_api.repository.TransacoesRepository;
import com.example.guiaBolso_api.service.TransacoesService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class transacoesController {

	@Autowired
    TransacoesRepository transacoesRepository;
    private TransacoesService transacoesService;
    
    public transacoesController(TransacoesService transacoesService) {
        this.transacoesService = transacoesService;
    }

    @GetMapping("/transacoes")
    public Iterable<Transacoes> list() {
        return transacoesService.list();
    }

	@GetMapping("/transacoes/{idTransacao}")
    public Transacoes listaTransacoes (@PathVariable(value = "idTransacao") int id){
		return transacoesRepository.findById(id);
	}
	
	@GetMapping("/{idUsuario}/transacoes/{ano}/{mes}")
	public List<TransacoesExibe> pegaTransacoes(@PathVariable(value = "idUsuario", required = true) int id,
			@PathVariable(value = "ano", required = true) int ano,
			@PathVariable(value = "mes", required = true) int mes) throws JsonParseException, JsonMappingException, IOException {
		
		return transacoesService.idAnoMes(id, ano, mes);
	}
}
