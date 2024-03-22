package com.orbitaltech.demo.service;

import com.orbitaltech.demo.adapter.api.ConsultaApi;
import com.orbitaltech.demo.dto.ClienteInputDto;
import com.orbitaltech.demo.dto.ViaCepEnderecoDTO;
import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.model.Endereco;
import com.orbitaltech.demo.repository.ClienteRepository;
import com.orbitaltech.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    ConsultaApi viaCepAdapter;
    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public void deletar(long id) {
        Cliente clienteParaSerDeletado = buscarOuFalhar(id);
        clienteRepository.delete(clienteParaSerDeletado);
    }

    public Cliente adicionar(ClienteInputDto clienteAdicionado) {
        ViaCepEnderecoDTO cepEnderecoDTO = viaCepAdapter.responseEndereco(clienteAdicionado.getCep());
        Cliente cliente = new Cliente();
        Endereco endereco = Endereco.builder()
                .logradouro(cepEnderecoDTO.getLogradouro())
                .cep(cepEnderecoDTO.getCep())
                .cidade(cepEnderecoDTO.getLocalidade())
                .uf(cepEnderecoDTO.getUf())
                .build();
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        cliente.setNome(clienteAdicionado.getNome());
        cliente.setDataNascimento(clienteAdicionado.getDataNascimento());
        cliente.getEnderecos().add(enderecoSalvo);

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(long clienteId, Cliente clienteAtualizado) {
        Cliente clienteParaSerAtualizado = buscarOuFalhar(clienteId);

        clienteParaSerAtualizado.setNome(clienteAtualizado.getNome());
        clienteParaSerAtualizado.setDataNascimento(clienteAtualizado.getDataNascimento());


        return clienteRepository.save(clienteParaSerAtualizado);
    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(
                () -> new IllegalArgumentException("Cliente não encontrado"));

    }
}




