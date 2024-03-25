package com.orbitaltech.demo.controller;

import com.orbitaltech.demo.adapter.api.ConsultaApi;
import com.orbitaltech.demo.controller.handlers.ControllerExceptionHandler;
import com.orbitaltech.demo.dto.ClienteInputDto;
import com.orbitaltech.demo.dto.ViaCepEnderecoDTO;
import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.model.Endereco;
import com.orbitaltech.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends ResponseEntityExceptionHandler {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ConsultaApi consultaApi;

    @GetMapping()
    public List<Cliente> clientes() {
        return clienteService.listarCliente();
    }

    @GetMapping("/{id}")
    public Cliente cliente(@PathVariable long id) {
        return clienteService.buscarOuFalhar(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        clienteService.deletar(id);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarCliente(@RequestBody ClienteInputDto clientejson) {
        return clienteService.adicionar(clientejson);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable long id, @RequestBody Cliente clientejson) {
        return clienteService.atualizar(id, clientejson);
    }

    @GetMapping("/teste/{viaCepEnderecoDTO}")
    public ViaCepEnderecoDTO adicionar(@PathVariable String viaCepEnderecoDTO) {
        return consultaApi.responseEndereco(viaCepEnderecoDTO).getBody();
    }

}
