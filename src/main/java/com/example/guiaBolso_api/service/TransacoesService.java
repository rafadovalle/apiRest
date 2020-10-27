package com.example.guiaBolso_api.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.guiaBolso_api.domain.Transacoes;
import com.example.guiaBolso_api.domain.TransacoesExibe;
import com.example.guiaBolso_api.repository.TransacoesRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class TransacoesService {

    private TransacoesRepository transacoesRepository;
    
    
    public TransacoesService(TransacoesRepository transacoesRepository) {
    	this.transacoesRepository = transacoesRepository;
    }
    
    public Iterable<Transacoes> list(){
    	return transacoesRepository.findAll();
    }
    
    public Transacoes save(Transacoes transacoes) {
    	return transacoesRepository.save(transacoes);
    }
    
    public Iterable<Transacoes> save(List<Transacoes> transacoes){
    	return transacoesRepository.saveAll(transacoes);
    }
    
    //Alterado para lista transacoesExibe DTO para trazer os dados do contrato
	public List<TransacoesExibe> idAnoMes(int idUsuario, int ano, int mes) {
    	List<Transacoes> list = new ArrayList<Transacoes>();
    	List<Transacoes> filterList = new ArrayList<Transacoes>();
    	List<TransacoesExibe> transacoesExibe = new ArrayList<TransacoesExibe>();
    	
    	list = lista();
    	
    	try{
    		filterList = list.stream().filter(object -> (idUsuario == object.getIdUsuario() 
    				&& ano == object.getAno() && mes == object.getMes())).collect(Collectors.toList());

    		for(int i = 0; i < filterList.size(); i++) {
    			TransacoesExibe objeto = new TransacoesExibe();
    			objeto.setDescricao(filterList.get(i).getDescricao());;
    			objeto.setDataTransacao(filterList.get(i).getDataTransacao());
    			objeto.setValor(filterList.get(i).getValor());
    			objeto.setDuplicated(filterList.get(i).getDuplicated());
    			
    			transacoesExibe.add(objeto);
    		}
    		
    	}catch(RuntimeException e) {
    		e.printStackTrace();
    	}
    	
    	return transacoesExibe;
    }
    
	//Método de manipulação JSON
    public List<Transacoes> lista(){
    	List<Transacoes> list = new ArrayList<Transacoes>();
    	
    	try {
	    	ObjectMapper mapper = new ObjectMapper();
	    	TypeReference<List<Transacoes>> typeReference = new TypeReference<List<Transacoes>>() {};
	    	InputStream inputStream = TypeReference.class.getResourceAsStream("/json/transacoes.json");
	    	List<Transacoes> transacoes = mapper.readValue(inputStream,typeReference);
	    	for(Transacoes t : transacoes) {
	    		save(t);
	    	}
	    	
	    	list = transacoes;
	    	
	    	}catch(FileNotFoundException e) {
	    		e.printStackTrace();
	    	}catch(JsonParseException e) {
	    		e.printStackTrace();
	    	}catch(JsonMappingException e) {
	    		e.printStackTrace();
	    	}catch(IOException e) {
	    		e.printStackTrace();
	    	}
    	
    	return list;
    }
    
}
