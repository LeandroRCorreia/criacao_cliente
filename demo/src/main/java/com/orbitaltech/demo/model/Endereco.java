package com.orbitaltech.demo.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    private Long id;
    private String cep;
    private String logradouro;
    private String cidade;
    private String uf;


}
