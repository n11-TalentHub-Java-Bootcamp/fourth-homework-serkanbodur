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

    //@Mapping(source = "debts", target = "debtDTOs")
    //@Mapping(source = "debts", target = "debtIds")
    CollectionDTO convertCollectionToCollectionDTO(Collection collection);

    //@Mapping(source = "debtDTOs", target = "debts")
    //@Mapping(source = "debtIds", target = "debts.id")
    Collection convertCollectionDTOToCollection(CollectionDTO collectionDTO);

    //@Mapping(source = "debts", target = "debtDTOs")
    //@Mapping(source = "debts.id", target = "debtIds")
    List<CollectionDTO> convertCollectionToCollectionDTOs(List<Collection> collection);
}
