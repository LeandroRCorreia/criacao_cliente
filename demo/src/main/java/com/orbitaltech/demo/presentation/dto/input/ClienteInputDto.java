package com.orbitaltech.demo.presentation.dto.input;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteInputDto {

    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;
    private String cep;

}
