package com.orbitaltech.demo.gateway.adapter;

import com.orbitaltech.demo.integration.api.ConsultaApiFeignClientIntegration;
import com.orbitaltech.demo.integration.dtoIntegration.ViaCepEnderecoDTO;
import com.orbitaltech.demo.integration.model.Endereco;
import com.orbitaltech.demo.service.exception.EnderecoNaoEncontradoException;
import com.orbitaltech.demo.service.exception.FormatoEnderecoInvalidoException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ConsultaEnderecoGatewayAdapterImpl implements ConsultaEnderecoGatewayAdapter {

    @Autowired
    private ConsultaApiFeignClientIntegration consultaApiFeignClientIntegration;


    @Override
    public Endereco consulta(String cep) {
        try {
            ResponseEntity<ViaCepEnderecoDTO> viaCepEnderecoDTOResponse = consultaApiFeignClientIntegration.responseEndereco(cep);
            return buscarOuFalharEndereco(viaCepEnderecoDTOResponse);
        } catch (FeignException e) {
            throw new FormatoEnderecoInvalidoException("O formato do endereço passado está errado. O formato correto é com 8 digitos");
        }
    }

    private Endereco buscarOuFalharEndereco(ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO) {
        if (cepEnderecoDTO.getBody().isResponseError()) {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado");
        }

        return retornaEndereco(cepEnderecoDTO);

    }

    private Endereco retornaEndereco(ResponseEntity<ViaCepEnderecoDTO> cepEnderecoDTO) {

        return Endereco.builder()
                .logradouro(cepEnderecoDTO.getBody().getLogradouro())
                .cep(cepEnderecoDTO.getBody().getCep())
                .cidade(cepEnderecoDTO.getBody().getLocalidade())
                .uf(cepEnderecoDTO.getBody().getUf())
                .build();
    }
}
