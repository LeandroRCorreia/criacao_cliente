package com.orbitaltech.demo.adapter.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="apiCep", url = "https://viacep.com.br/ws/{cep}/json/")
public interface ConsultaApi {
    @GetMapping("{cep}")
    void getEndereco(@PathVariable String cep);

}
