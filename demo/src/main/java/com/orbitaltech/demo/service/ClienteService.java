package com.orbitaltech.demo.service;

import com.orbitaltech.demo.gateway.adapter.ConsultaEnderecoGatewayAdapter;
import com.orbitaltech.demo.integration.api.ConsultaApiFeignClientIntegration;
import com.orbitaltech.demo.integration.model.Cliente;
import com.orbitaltech.demo.integration.model.Endereco;
import com.orbitaltech.demo.integration.repository.ClienteRepository;
import com.orbitaltech.demo.integration.repository.EnderecoRepository;
import com.orbitaltech.demo.presentation.dto.input.ClienteInputDto;
import com.orbitaltech.demo.service.exception.ClienteNaoEncontradoException;
import com.orbitaltech.demo.service.exception.NegocioException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    ConsultaApiFeignClientIntegration viaCepAdapter;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ConsultaEnderecoGatewayAdapter consultaEnderecoGatewayAdapter;

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
        enderecoSalvo = enderecoRepository.save(enderecoSalvo);

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
        return consultaEnderecoGatewayAdapter.consulta(clienteAdicionado.getCep());
    }

}
