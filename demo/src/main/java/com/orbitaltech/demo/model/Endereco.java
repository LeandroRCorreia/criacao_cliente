package com.orbitaltech.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
