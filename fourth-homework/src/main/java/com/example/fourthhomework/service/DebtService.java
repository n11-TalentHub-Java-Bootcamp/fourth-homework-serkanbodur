package com.example.fourthhomework.service;

import com.example.fourthhomework.dto.DebtDTO;
import com.example.fourthhomework.enums.EnumDebtType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface DebtService {
    DebtDTO save(DebtDTO debtDTO);
    List<DebtDTO> findAllByCreatedDateBetween(Date startDate, Date endDate);
    List<DebtDTO> findAllByUserId(Long userId);
    List<DebtDTO> findAllByUserIdAndExpiryDateBeforeAndRealDebtAmountIsNotZero(Long userId);
    BigDecimal sumInstantDebtByUserId(Long userId);
    BigDecimal sumRealDebtByUserIdAndExpiryDate(Long userId);
    BigDecimal instantLateRaise(Long userId);
    BigDecimal lateRaiseDebt(Date startDate, Date enddate, BigDecimal rate, BigDecimal mainDebtAmount);
}
