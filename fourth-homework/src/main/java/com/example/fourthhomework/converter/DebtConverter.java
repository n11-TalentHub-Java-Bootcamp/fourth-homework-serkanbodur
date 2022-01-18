package com.example.fourthhomework.converter;

import com.example.fourthhomework.dto.DebtDTO;
import com.example.fourthhomework.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtConverter {

    DebtConverter INSTANCE = Mappers.getMapper(DebtConverter.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "collection.id", target = "collectionId")
    @Mapping(source = "mainDebt.id", target = "mainDebtId")
    DebtDTO convertDebtToDebtDTO(Debt debt);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "collectionId", target = "collection.id")
    @Mapping(source = "mainDebtId", target = "mainDebt.id")
    Debt convertDebtDTOToDebt(DebtDTO debtDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "collection.id", target = "collectionId")
    @Mapping(source = "mainDebt.id", target = "mainDebtId")
    List<DebtDTO> convertAllDebtToDebtDTOs(List<Debt> debt);
}
