package com.orbitaltech.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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
            joinColumns = @JoinColumn(name = "ClientesId"),
            inverseJoinColumns = @JoinColumn(name = "EnderecoId")
    )
    private Set<Endereco> enderecos = new HashSet<>();


}
