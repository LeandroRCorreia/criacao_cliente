package com.orbitaltech.demo.service;

import com.orbitaltech.demo.adapter.api.ConsultaApi;
import com.orbitaltech.demo.dto.ClienteInputDto;
import com.orbitaltech.demo.dto.ViaCepEnderecoDTO;
import com.orbitaltech.demo.exception.ClienteNaoEncontradoException;
import com.orbitaltech.demo.exception.EnderecoNaoEncontradoException;
import com.orbitaltech.demo.exception.FormatoEnderecoInvalidoException;
import com.orbitaltech.demo.exception.NegocioException;
import com.orbitaltech.demo.model.Cliente;
import com.orbitaltech.demo.model.Endereco;
import com.orbitaltech.demo.repository.ClienteRepository;
import com.orbitaltech.demo.repository.EnderecoRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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
            clienteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado.");
        } catch (DataIntegrityViolationException e) {
            throw new NegocioException("Cliente a ser deletado, não pode ser deletado");
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
    public Cliente atualizar(long clienteId, ClienteInputDto clienteAdicionado) {

        Cliente clienteParaSerAtualizado = buscarOuFalhar(clienteId);

        clienteParaSerAtualizado.setNome(clienteAdicionado.getNome());
        clienteParaSerAtualizado.setDataNascimento(clienteAdicionado.getDataNascimento());
        Endereco endereco = copiaEnderecoParaCliente(clienteAdicionado);
        clienteParaSerAtualizado.getEnderecos().set(0, endereco);
        return clienteRepository.save(clienteParaSerAtualizado);
    }

    public Cliente buscarOuFalhar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(
                () -> new ClienteNaoEncontradoException("Cliente não encontrado"));
    }


    private Endereco copiaEnderecoParaCliente(ClienteInputDto clienteAdicionado) {
        try {
            ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO = viaCepAdapter.responseEndereco(clienteAdicionado.getCep());
            return buscarOuFalharEndereco(cepEnderecoDTO);
        } catch (FeignException e) {
            throw new FormatoEnderecoInvalidoException("O formato do endereço passado está errado. O formato correto é com 8 digitos");
        }

    }

    private Endereco buscarOuFalharEndereco(ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO) {
        if (cepEnderecoDTO.getBody().isResponseError()) {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado");
        }

        return salvaEndereco(cepEnderecoDTO);

    }

    private Endereco salvaEndereco(ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO) {
        Endereco endereco = Endereco.builder()
                .logradouro(cepEnderecoDTO.getBody().getLogradouro())
                .cep(cepEnderecoDTO.getBody().getCep())
                .cidade(cepEnderecoDTO.getBody().getLocalidade())
                .uf(cepEnderecoDTO.getBody().getUf())
                .build();

        return enderecoRepository.save(endereco);
    }

}
