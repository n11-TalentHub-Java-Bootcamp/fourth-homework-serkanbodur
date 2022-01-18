package com.example.fourthhomework.converter;

import com.example.fourthhomework.dto.CollectionDTO;
import com.example.fourthhomework.entity.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionConverter {

    CollectionConverter INSTANCE = Mappers.getMapper(CollectionConverter.class);

    CollectionDTO convertCollectionToCollectionDTO(Collection collection);

    Collection convertCollectionDTOToCollection(CollectionDTO collectionDTO);

    List<CollectionDTO> convertCollectionToCollectionDTOs(List<Collection> collection);
}
