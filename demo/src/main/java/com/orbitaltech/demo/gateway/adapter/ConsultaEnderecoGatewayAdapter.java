package com.orbitaltech.demo.gateway.adapter;

import com.orbitaltech.demo.integration.model.Endereco;

public interface ConsultaEnderecoGatewayAdapter {
    Endereco consulta(String cep);
}
