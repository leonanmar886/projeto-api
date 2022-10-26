package com.projeto.projetoapi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<Cliente> listar(){
        Log log = new Log();
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        log.setData(data);
        log.setHora(hora);
        log.setTipo("GET");
        logRepository.save(log);
        return clienteRepository.findAll();
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    public Cliente adicionar(@RequestBody Cliente cliente){
        Log log = new Log();
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        log.setData(data);
        log.setHora(hora);
        log.setTipo("POST");
        logRepository.save(log);
        return clienteRepository.save(cliente);
    }

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

    @RequestMapping(value = "/clientes/{campo}", method = RequestMethod.GET)
    public Cliente procuraCliente(@PathVariable String campo){
        if(isNumeric(campo) == true){
            Cliente pessoa1;
            if(campo.length() == 11){
                pessoa1 = clienteRepository.findByCpf(Long.parseLong(campo));
            }else if(campo.length() == 9 ){
                pessoa1 = clienteRepository.findByRg(Long.parseLong(campo));
            } else {
                pessoa1 = new Cliente();
            }
            Log log = new Log();
            Date dataHoraAtual = new Date();
            String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
            String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            log.setData(data);
            log.setHora(hora);
            log.setTipo("GET");
            logRepository.save(log);
            return pessoa1;
        } else {
            List<Cliente> pessoa3 = clienteRepository.findByNome(campo);
            Log log = new Log();
            Date dataHoraAtual = new Date();
            String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
            String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            log.setData(data);
            log.setHora(hora);
            log.setTipo("GET");
            logRepository.save(log);
            return pessoa3.get(0);
        }
    } 

    @RequestMapping(value = "/clientes/{campo}", method = RequestMethod.DELETE)
    public void excluiCliente(@PathVariable String campo){
        if(isNumeric(campo) == true){
            Cliente pessoa1;
            if(campo.length() == 11){
                pessoa1 = clienteRepository.findByCpf(Long.parseLong(campo));
            }else if(campo.length() == 9 ){
                pessoa1 = clienteRepository.findByRg(Long.parseLong(campo));
            } else {
                pessoa1 = new Cliente();
            }
            clienteRepository.delete(pessoa1);
        } else {
            List<Cliente> pessoa3 = clienteRepository.findByNome(campo);
            clienteRepository.delete(pessoa3.get(0));
        }
        Log log = new Log();
        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        log.setData(data);
        log.setHora(hora);
        log.setTipo("DELETE");
        logRepository.save(log);
    }
}
