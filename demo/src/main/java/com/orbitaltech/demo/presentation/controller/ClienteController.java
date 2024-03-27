package com.orbitaltech.demo.presentation.controller;


import com.orbitaltech.demo.presentation.dto.model.ClienteModelDto;
import com.orbitaltech.demo.presentation.mapper.ClienteMapper;
import com.orbitaltech.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.orbitaltech.demo.presentation.dto.input.ClienteInputDto;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping()
    public List<ClienteModelDto> clientes() {
        return ClienteMapper.CLIENTE_MAPPER.toCollectionDto(clienteService.listarCliente());

    }

    @GetMapping("/{id}")
    public ClienteModelDto cliente(@PathVariable long id) {

        return  ClienteMapper.CLIENTE_MAPPER.toDto(clienteService.buscarOuFalhar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        clienteService.deletar(id);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModelDto adicionarCliente(@RequestBody ClienteInputDto clienteInput) {
        return ClienteMapper.CLIENTE_MAPPER.toDto(clienteService.adicionar(clienteInput));
    }

    @PutMapping("/{id}")
    public ClienteModelDto atualizarCliente(@PathVariable long id, @RequestBody ClienteInputDto clienteInput) {
        return ClienteMapper.CLIENTE_MAPPER.toDto(clienteService.atualizar(id, clienteInput));
    }

}
