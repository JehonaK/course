package com.course.util;

import com.course.dto.DtoEntity;
import com.course.entity.BaseEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static DtoEntity convertToDto(Object obj, Class<? extends DtoEntity> asd) {
        return new ModelMapper().map(obj, asd);
    }

    public static List<DtoEntity> convertToDTOList(List<? extends BaseEntity> objectList, Class<? extends DtoEntity> mapper) {
        return objectList.stream()
                .map(e -> convertToDto(e, mapper))
                .collect(Collectors.toList());
    }

}
