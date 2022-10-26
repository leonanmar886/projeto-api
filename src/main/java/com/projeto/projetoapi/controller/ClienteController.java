package com.projeto.projetoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.projetoapi.model.Cliente;
import com.projeto.projetoapi.repository.ClienteRepository;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    public Cliente adicionar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @RequestMapping(value = "/clientes/{nome}", method = RequestMethod.GET)
    public List<Cliente> procuraNome(@PathVariable(value = "nome") String nome){
        List<Cliente> pessoas = clienteRepository.findByNome(nome);
        return pessoas;
    }
    
}
