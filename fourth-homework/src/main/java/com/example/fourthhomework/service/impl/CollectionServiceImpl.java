package com.example.fourthhomework.service.impl;

import com.example.fourthhomework.converter.CollectionConverter;
import com.example.fourthhomework.converter.DebtConverter;
import com.example.fourthhomework.dto.CollectionDTO;
import com.example.fourthhomework.dto.DebtDTO;
import com.example.fourthhomework.entity.Collection;
import com.example.fourthhomework.entity.Debt;
import com.example.fourthhomework.enums.EnumDebtType;
import com.example.fourthhomework.exception.DebtIsNotExistException;
import com.example.fourthhomework.repository.CollectionRepository;
import com.example.fourthhomework.repository.DebtRepository;
import com.example.fourthhomework.service.CollectionService;
import com.example.fourthhomework.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final DebtRepository debtRepository;
    private final DebtService debtService;


    @Override
    public CollectionDTO save(CollectionDTO collectionDTO) {
        var collection = CollectionConverter.INSTANCE.convertCollectionDTOToCollection(collectionDTO);
        collection.setCollectionDate(new Date());
        collection = collectionRepository.save(collection);

        var debt = debtRepository.findById(collectionDTO.getDebtId()).orElseThrow(() -> new DebtIsNotExistException("The debt was not found"));

        debt.setRealDebtAmount(BigDecimal.valueOf(0));
        debt.setCollection(collection);

        if(debt.getExpiryDate().before(new Date()))
        {
            BigDecimal totalDebt = BigDecimal.valueOf(0);
            var lateDebtDTO = new DebtDTO();

            lateDebtDTO.setDebtType(EnumDebtType.LATE_FEE);
            lateDebtDTO.setMainDebtId(debt.getId());
            lateDebtDTO.setRealDebtAmount(BigDecimal.valueOf(0));
            lateDebtDTO.setUserId(debt.getUser().getId());
            lateDebtDTO.setCollectionId(collection.getId());

            if(debt.getExpiryDate().before(DebtServiceImpl.date))
            {
                totalDebt = debtService.lateRaiseDebt(debt.getExpiryDate(),DebtServiceImpl.date,  DebtServiceImpl.bRate,debt.getMainDebtAmount());
                totalDebt = totalDebt.add(debtService.lateRaiseDebt( DebtServiceImpl.date, new Date(), DebtServiceImpl.aRate, debt.getMainDebtAmount()));
            }
            else
            {
                totalDebt = debtService.lateRaiseDebt(debt.getExpiryDate(), new Date(), DebtServiceImpl.aRate, debt.getMainDebtAmount());
            }
            lateDebtDTO.setMainDebtAmount(totalDebt);
            var lateDebt = DebtConverter.INSTANCE.convertDebtDTOToDebt(lateDebtDTO);
            lateDebt = debtRepository.save(lateDebt);
        }
        return CollectionConverter.INSTANCE.convertCollectionToCollectionDTO(collection);
    }

    @Override
    public List<CollectionDTO> findAllByCollectionDateBetween(Date startDate, Date endDate) {
        var collectionList = collectionRepository.findAllByCollectionDateBetween(startDate, endDate);
        return CollectionConverter.INSTANCE.convertCollectionToCollectionDTOs(collectionList);
    }

    @Override
    public List<CollectionDTO> findAllByUserId(Long userId) {
        var collectionList = collectionRepository.findAllByUserId(userId);
        return CollectionConverter.INSTANCE.convertCollectionToCollectionDTOs(collectionList);
    }

    @Override
    public BigDecimal sumMainDebtAmountByUserId(Long userId) {
        return collectionRepository.sumMainDebtAmountByUserId(userId);
    }
}
