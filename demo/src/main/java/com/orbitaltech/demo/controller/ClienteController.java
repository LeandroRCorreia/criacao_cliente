package com.orbitaltech.demo.controller;

import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> Clientes()
    {
        List<Cliente> clientesList = clienteRepository.findAll();

        return clientesList;
    }

    @GetMapping("/cliente/{id}")
    public Cliente Cliente(@PathVariable long id)
    {
        Cliente cliente = clienteRepository.
                          findById(id).
                          orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return cliente;
    }

    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable long id)
    {
        Cliente cliente = clienteRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteRepository.deleteById(cliente.getId());

    }

}
