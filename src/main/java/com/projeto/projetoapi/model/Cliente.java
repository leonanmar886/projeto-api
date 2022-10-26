package com.projeto.projetoapi.model;

import java.util.Date;

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
    private Long CPF;
    private Long RG;
    private String nome;
    private String nomeMae;
    private Date dataNascimento;
    private Date dataCadastro;

    public Long GetId(){
        return id;
    }

    public void SetId(Long id){
        this.id = id;
    }

    public Long GetCPF(){
        return CPF;
    }

    public void SetCPF(Long CPF){
        this.CPF = CPF;
    }

    public Long GetRG(){
        return RG;
    }

    public void SetRG(Long RG){
        this.RG = RG;
    }

    public String GetNome(){
        return nome;
    }

    public void SetNome(String nome){
        this.nome = nome;
    }

    public String GetNomeMae(){
        return nomeMae;
    }

    public void SetNomeMae(String nomeMae){
        this.nomeMae = nomeMae;
    }

    public Date GetDataNascimento(){
        return dataNascimento;
    }

    public void SetDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public Date GetDataCadastro(){
        return dataCadastro;
    }

    public void SetDataCadastro(Date dataCadastro){
        this.dataCadastro = dataCadastro;
    }

}
