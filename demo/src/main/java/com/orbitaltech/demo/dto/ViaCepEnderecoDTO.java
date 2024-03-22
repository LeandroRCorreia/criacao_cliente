package com.orbitaltech.demo.dto;

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
    private String error;

    public Boolean isResponseError() {
        return StringUtils.isNotEmpty(error);

    }
}
