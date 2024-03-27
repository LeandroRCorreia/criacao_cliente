package com.orbitaltech.demo.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "clientes")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;

    @ManyToMany
    @JoinTable(
            name = "enderecoCliente",
            joinColumns = @JoinColumn(name = "fk_clientes_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_endereco_id")
    )
    private List<Endereco> enderecos = new ArrayList<>();


}
