package com.orbitaltech.demo.integration.dtoIntegration;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class ViaCepEnderecoDTO {
    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String erro;

    public Boolean isResponseError() {
        return StringUtils.isNotEmpty(erro);

    }
}
