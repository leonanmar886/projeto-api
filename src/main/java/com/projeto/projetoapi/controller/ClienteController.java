package com.projeto.projetoapi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.projetoapi.model.Cliente;
import com.projeto.projetoapi.model.Log;
import com.projeto.projetoapi.repository.ClienteRepository;
import com.projeto.projetoapi.repository.LogRepository;

@RestController
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LogRepository logRepository;

    // função que verifica se uma string é numérica.
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // função que verifica qual o tipo do campo. 1 = CPF, 2 = RG, 3 = Nome e 0 = Erro.
    public int verificacaoCampo(String campo){
        if(isNumeric(campo) == true){
            if(campo.length() == 11) {
                return 1;
            } else if(campo.length() == 9 ) {
                return 2;
            } else {
                return 0;
            }
        } else {
            return 3;
        }
    }

    // função que forma um novo log.
    public Log formarLog(String tipo){
        Log log = new Log();
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        log.setData(data);
        log.setHora(hora);
        log.setTipo(tipo); 
        return log; 
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<Cliente> listar(){
        logRepository.save(formarLog("GET")); //um novo log é montado com a data e hora atual, e depois é salvo no banco de dados.
        return clienteRepository.findAll();
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    public Cliente adicionar(@RequestBody Cliente cliente){
        logRepository.save(formarLog("POST")); //um novo log é montado com a data e hora atual, e depois é salvo no banco de dados.
        return clienteRepository.save(cliente);
    }

    @RequestMapping(value = "/clientes/{campo}", method = RequestMethod.GET)
    public Cliente procuraCliente(@PathVariable String campo){
        Cliente pessoa1;
        switch(verificacaoCampo(campo)) {
            case 1:
                pessoa1 = clienteRepository.findByCpf(Long.parseLong(campo));
            break;

            case 2:
                pessoa1 = clienteRepository.findByRg(Long.parseLong(campo));
            break;

            case 3:
                List<Cliente> pessoa3 = clienteRepository.findByNome(campo);
                return pessoa3.get(0);

            default:
                pessoa1 = new Cliente();
            break;
        }
        logRepository.save(formarLog("GET"));
        return pessoa1;
    } 

    @RequestMapping(value = "/clientes/{campo}", method = RequestMethod.DELETE)
    public void excluiCliente(@PathVariable String campo){
        Cliente pessoa1;

        switch(verificacaoCampo(campo)){
            case 1:
                pessoa1 = clienteRepository.findByCpf(Long.parseLong(campo));
            break;

            case 2:
                pessoa1 = clienteRepository.findByRg(Long.parseLong(campo));
            break;

            default:
                pessoa1 = new Cliente();
            break;
        }

        clienteRepository.delete(pessoa1);
        logRepository.save(formarLog("DELETE"));
    }
}
