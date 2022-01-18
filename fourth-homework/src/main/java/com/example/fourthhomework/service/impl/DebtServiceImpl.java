package com.example.fourthhomework.service.impl;

import com.example.fourthhomework.converter.DebtConverter;
import com.example.fourthhomework.dto.DebtDTO;
import com.example.fourthhomework.entity.Collection;
import com.example.fourthhomework.entity.Debt;
import com.example.fourthhomework.entity.User;
import com.example.fourthhomework.enums.EnumDebtType;
import com.example.fourthhomework.repository.DebtRepository;
import com.example.fourthhomework.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.helpers.Util;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;

    public static final Date date = Date.from(Instant.parse("2018-01-01T00:00:00.000Z"));
    public static final BigDecimal bRate = BigDecimal.valueOf(0.015);
    public static final BigDecimal aRate = BigDecimal.valueOf(0.02);

    @Override
    public DebtDTO save(DebtDTO debtDTO) {
        var debt = DebtConverter.INSTANCE.convertDebtDTOToDebt(debtDTO);
        debt.setCreatedDate(new Date());
        debt = debtRepository.save(debt);
        return DebtConverter.INSTANCE.convertDebtToDebtDTO(debt);
    }

    @Override
    public List<DebtDTO> findAllByCreatedDateBetween(Date startDate, Date endDate) {
        var debtList = debtRepository.findAllByCreatedDateBetween(startDate,endDate);
        return DebtConverter.INSTANCE.convertAllDebtToDebtDTOs(debtList);
    }

    @Override
    public List<DebtDTO> findAllByUserId(Long userId) {
        var debtList = debtRepository.findAllByUserId(userId);
        return DebtConverter.INSTANCE.convertAllDebtToDebtDTOs(debtList);
    }

    @Override
    public List<DebtDTO> findAllByUserIdAndExpiryDateBeforeAndRealDebtAmountIsNotZero(Long userId) {
        var debtList = debtRepository.findAllByUserIdAndExpiryDateBeforeAndRealDebtAmountIsNot(userId, new Date(), BigDecimal.valueOf(0));
        return DebtConverter.INSTANCE.convertAllDebtToDebtDTOs(debtList);
    }

    @Override
    public BigDecimal sumInstantDebtByUserId(Long userId) {

        var debtList = debtRepository.findAllByUserIdAndAndDebtType(userId);
        BigDecimal totalLateRaise = BigDecimal.valueOf(0).setScale(2);
        BigDecimal tempLateRaise = BigDecimal.valueOf(0).setScale(2);

        for(Debt debt : debtList)
        {
            if(debt.getExpiryDate().before(new Date()) && debt.getExpiryDate().before(date))
            {
                tempLateRaise = lateRaiseDebt(debt.getExpiryDate(), date, bRate, debt.getMainDebtAmount());
                tempLateRaise = tempLateRaise.add(lateRaiseDebt(date, new Date(), aRate, debt.getMainDebtAmount()));
            }
            if(debt.getExpiryDate().before(new Date()) && debt.getExpiryDate().after(date))
            {
                tempLateRaise = lateRaiseDebt(debt.getExpiryDate(), new Date(),  aRate, debt.getMainDebtAmount());
            }
            debt.setLateFee(totalLateRaise);
            totalLateRaise = totalLateRaise.add(tempLateRaise.add(debt.getMainDebtAmount()));
        }

        return totalLateRaise;
    }

    @Override
    public BigDecimal sumRealDebtByUserIdAndExpiryDate(Long userId) {
        return debtRepository.sumRealDebtByUserIdAndAndExpiryDateBefore(userId, new Date());
    }

    // The function that calculate the Late Raise
    @Override
    public BigDecimal lateRaiseDebt(Date startDate, Date endDate, BigDecimal rate, BigDecimal mainDebtAmount) {
        var dayDifference = endDate.getTime() - startDate.getTime();
        var days = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
        var lateRaise = BigDecimal.valueOf(days).multiply(rate.multiply(mainDebtAmount));
        lateRaise.setScale(2, RoundingMode.UP);
        return lateRaise;
    }

    @Override
    public BigDecimal instantLateRaise(Long userId) {

        var debts = debtRepository.findAllByUserId(userId);

        BigDecimal totalDebt = BigDecimal.valueOf(0).setScale(2);
        for(Debt debt : debts)
        {
            // If expiryDate is before 01.01.2018, first the days which before 2018 are calculated with 1.5 rateRaise
            // Then the days which after 2018 are calculated 2.0 rateRaise
            if(debt.getExpiryDate().before(new Date()) && debt.getExpiryDate().before(date))
            {
                totalDebt = lateRaiseDebt(debt.getExpiryDate(),date,bRate,debt.getMainDebtAmount());
                totalDebt = totalDebt.add(lateRaiseDebt(date, new Date(),  aRate, debt.getMainDebtAmount()));
            }

            // If expiryDate is after 01.01.2018, we can calculate directly using 2.0, because we will being passed with all the date which created "now"
            if(debt.getExpiryDate().before(new Date()) && debt.getExpiryDate().after(date))
            {
                totalDebt = lateRaiseDebt(debt.getExpiryDate(), new Date(),  aRate, debt.getMainDebtAmount());
            }
        }
        return totalDebt;
    }


}
