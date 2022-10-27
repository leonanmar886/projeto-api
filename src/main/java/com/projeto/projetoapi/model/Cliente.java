package com.projeto.projetoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cpf; //cpf foi definido com 11 digitos
    private Long rg; //rg foi definido com 9 dígitos.
    private String nome;
    private String mae;
    private String nascimento;
    private String cadastro;

}
