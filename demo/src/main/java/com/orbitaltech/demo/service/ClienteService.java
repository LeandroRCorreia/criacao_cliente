package com.orbitaltech.demo.service;

import com.orbitaltech.demo.adapter.api.ConsultaApi;
import com.orbitaltech.demo.dto.ClienteInputDto;
import com.orbitaltech.demo.dto.ViaCepEnderecoDTO;
import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.model.Endereco;
import com.orbitaltech.demo.repository.ClienteRepository;
import com.orbitaltech.demo.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @Transactional
    public void deletar(long id) {
        Cliente clienteParaSerDeletado = buscarOuFalhar(id);
        try {
            clienteRepository.delete(clienteParaSerDeletado);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException();
            // TODO fazer exceção específica para o delete
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException();
            // TODO fazer exceção específica para o delete
        }

    }

    @Transactional
    public Cliente adicionar(ClienteInputDto clienteAdicionado) {
        Cliente cliente = new Cliente();

        Endereco enderecoSalvo = copiaEnderecoParaCliente(clienteAdicionado);

        cliente.setNome(clienteAdicionado.getNome());
        cliente.setDataNascimento(clienteAdicionado.getDataNascimento());
        cliente.getEnderecos().add(enderecoSalvo);

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(long clienteId, Cliente clienteAtualizado) {

        Cliente clienteParaSerAtualizado = buscarOuFalhar(clienteId);

        clienteParaSerAtualizado.setNome(clienteAtualizado.getNome());
        clienteParaSerAtualizado.setDataNascimento(clienteAtualizado.getDataNascimento());


        return clienteRepository.save(clienteParaSerAtualizado);
    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(
                () -> new IllegalArgumentException("Cliente não encontrado"));

        // TODO fazer exceção de cliente não encontrado

    }


    private Endereco copiaEnderecoParaCliente(ClienteInputDto clienteAdicionado) {
        ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO = viaCepAdapter.responseEndereco(clienteAdicionado.getCep());

        buscarOuFalharEndereco(cepEnderecoDTO);

        Endereco endereco = Endereco.builder()
                .logradouro(cepEnderecoDTO.getBody().getLogradouro())
                .cep(cepEnderecoDTO.getBody().getCep())
                .cidade(cepEnderecoDTO.getBody().getLocalidade())
                .uf(cepEnderecoDTO.getBody().getUf())
                .build();

        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoSalvo;
    }

    private static void buscarOuFalharEndereco(ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO) {
        if (cepEnderecoDTO.getBody().isResponseError()) {
            throw new RuntimeException();
            // TODO fazer o retorno de endereco não encontrado
        }
        if (cepEnderecoDTO.getStatusCode().is4xxClientError()) {
            throw new RuntimeException();
            // TODO fazer o retorno de formato do endereço descrito está errado não encontrado
        }
    }



}




