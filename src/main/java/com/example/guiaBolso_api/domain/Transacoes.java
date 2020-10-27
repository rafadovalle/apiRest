package com.example.guiaBolso_api.domain;


import java.io.Serializable;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transacoes implements Serializable{
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5603016709651461807L;
	
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Id
	private Integer idTransacao;
	private Integer idUsuario;
    private String descricao;
    private String dataTransacao;
    private Integer ano;
    private Integer mes;
    private Integer valor;
    private Boolean duplicated;
	
}
