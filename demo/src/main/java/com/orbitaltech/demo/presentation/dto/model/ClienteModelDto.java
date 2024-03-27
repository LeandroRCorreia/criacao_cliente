package com.orbitaltech.demo.presentation.dto.model;


import com.orbitaltech.demo.integration.model.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClienteModelDto {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private List<Endereco> enderecos = new ArrayList<>();

}

