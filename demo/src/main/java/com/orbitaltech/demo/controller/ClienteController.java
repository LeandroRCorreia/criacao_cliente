package com.orbitaltech.demo.controller;

import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping()
    public List<Cliente> clientes()
    {
        List<Cliente> clientesList = clienteRepository.findAll();

        return clientesList;
    }

    @GetMapping("/{id}")
    public Cliente cliente(@PathVariable long id)
    {
        Cliente cliente = getCliente(id);

        return cliente;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id)
    {
        Cliente cliente = getCliente(id);

        clienteRepository.deleteById(cliente.getId());
    }


    @PostMapping()
    public Cliente adicionarCliente(@RequestBody Cliente cliente)
    {
        Cliente cliente1 = clienteRepository.save(cliente);

        return cliente1;
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable long id, @RequestBody Cliente cliente)
    {
        Cliente cliente1 = getCliente(id);

        cliente1.setNome(cliente.getNome());
        cliente1.setDataNascimento(cliente.getDataNascimento());
        clienteRepository.save(cliente1);

        return cliente1;
    }

    private Cliente getCliente(long id)
    {
        Cliente cliente = clienteRepository.
                findById(id).
                orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return cliente;
    }

}
