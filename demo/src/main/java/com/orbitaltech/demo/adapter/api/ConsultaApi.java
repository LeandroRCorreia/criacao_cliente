package com.orbitaltech.demo.adapter.api;

import com.orbitaltech.demo.dto.ViaCepEnderecoDTO;
import com.orbitaltech.demo.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "apiCep", url = "https://viacep.com.br/ws")
public interface ConsultaApi {
    @GetMapping("/{cep}/json/")
    public ViaCepEnderecoDTO responseEndereco(@PathVariable("cep") String cep) ;

}
