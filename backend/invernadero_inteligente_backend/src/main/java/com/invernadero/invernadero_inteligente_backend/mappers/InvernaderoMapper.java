package com.invernadero.invernadero_inteligente_backend.mappers;

import com.invernadero.invernadero_inteligente_backend.dtos.response.InvernaderoResponseDTO;
import com.invernadero.invernadero_inteligente_backend.models.entities.Invernadero;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper para Invernadero
 * Ejemplo de template para otros mappers
 */
@Component
public class InvernaderoMapper implements IMapper<Invernadero, InvernaderoResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InvernaderoResponseDTO toDTO(Invernadero entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, InvernaderoResponseDTO.class);
    }

    @Override
    public Invernadero toEntity(InvernaderoResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, Invernadero.class);
    }
}

