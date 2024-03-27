package com.orbitaltech.demo.integration.api;

import com.orbitaltech.demo.integration.dtoIntegration.ViaCepEnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "apiCep", url = "https://viacep.com.br/ws")
public interface ConsultaApiFeignClientIntegration {
    @GetMapping("/{cep}/json/")
     ResponseEntity<ViaCepEnderecoDTO> responseEndereco(@PathVariable("cep") String cep) ;

}
