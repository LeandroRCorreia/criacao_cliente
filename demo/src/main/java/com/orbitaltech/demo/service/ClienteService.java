package com.orbitaltech.demo.service;

import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public void deletar(long id) {
        Cliente clienteParaSerDeletado = buscarOuFalhar(id);
        clienteRepository.delete(clienteParaSerDeletado);
    }

    public Cliente adicionar(Cliente clienteAdicionado) {
        return clienteRepository.save(clienteAdicionado);
    }

    public Cliente atualizar(long clienteId, Cliente clienteAtualizado) {
        Cliente clienteParaSerAtualizado = buscarOuFalhar(clienteId);

        clienteParaSerAtualizado.setNome(clienteAtualizado.getNome());
        clienteParaSerAtualizado.setDataNascimento(clienteAtualizado.getDataNascimento());


        return clienteRepository.save(clienteAtualizado);
    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(
                () -> new IllegalArgumentException("Cliente não encontrado"));

    }
}
