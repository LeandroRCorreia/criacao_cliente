package com.orbitaltech.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepEnderecoDTO {
    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
}
