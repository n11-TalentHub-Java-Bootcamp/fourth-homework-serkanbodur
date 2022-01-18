package com.example.fourthhomework.service;

import com.example.fourthhomework.dto.CollectionDTO;
import com.example.fourthhomework.entity.Collection;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CollectionService {
    CollectionDTO save(CollectionDTO collectionDTO);
    List<CollectionDTO> findAllByCollectionDateBetween(Date startDate, Date endDate);
    List<CollectionDTO> findAllByUserId(Long userId);
    BigDecimal sumMainDebtAmountByUserId(Long userId);
}
