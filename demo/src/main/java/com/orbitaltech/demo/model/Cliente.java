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
    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @ManyToMany
    @JoinTable(
            name = "enderecoCliente",
            joinColumns = @JoinColumn(name = "fk_clientes_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_endereco_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();


}
