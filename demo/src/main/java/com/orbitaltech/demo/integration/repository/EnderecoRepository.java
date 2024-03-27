package com.orbitaltech.demo.integration.repository;

import com.orbitaltech.demo.integration.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
