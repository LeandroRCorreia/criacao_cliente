package com.orbitaltech.demo.presentation.mapper;

import com.orbitaltech.demo.integration.model.Cliente;
import com.orbitaltech.demo.presentation.dto.model.ClienteModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ClienteMapper {

    public static final ClienteMapper CLIENTE_MAPPER = Mappers.getMapper(ClienteMapper.class);

    public abstract Cliente toModel(ClienteModelDto clienteModelDto);

    public abstract ClienteModelDto toDto(Cliente cliente);

    public abstract List<ClienteModelDto> toCollectionDto(List<Cliente> clientes);
}
