package com.orbitaltech.demo.service;

import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findClienteById(id).
                orElseThrow(() -> new IllegalArgumentException("cliente não encontrado"));

        clienteAtualizado.setNome(clienteAtualizado.getNome());
        clienteAtualizado.setDataDeNascimento(clienteAtualizado.getDataDeNascimento());

        return clienteRepository.save(cliente);
    }
}
