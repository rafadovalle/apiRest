package com.example.guiaBolso_api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacoesExibe{

	//DTO
	private String descricao;
    private String dataTransacao;
    private Integer valor;
    private Boolean duplicated;
    	
}
