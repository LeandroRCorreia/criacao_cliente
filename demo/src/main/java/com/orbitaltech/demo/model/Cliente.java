package com.orbitaltech.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
