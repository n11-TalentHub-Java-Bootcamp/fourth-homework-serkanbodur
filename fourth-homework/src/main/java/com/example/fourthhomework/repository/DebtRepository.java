package com.example.fourthhomework.repository;

import com.example.fourthhomework.entity.Debt;
import com.example.fourthhomework.enums.EnumDebtType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findAllByCreatedDateBetween(Date startDate, Date endDate);
    List<Debt> findAllByUserId(Long userId);
    List<Debt> findAllByUserIdAndExpiryDateBeforeAndRealDebtAmountIsNot(Long userId, Date moment, BigDecimal realDebtAmount);

    @Query("select d from Debt d where d.user.id= :userId and d.debtType = 1")
    List<Debt> findAllByUserIdAndAndDebtType(Long userId);
    /*
    @Query("select sum(d.realDebtAmount) from Debt d where d.user.id= ?1")
    BigDecimal sumRealDebtByUserId(Long userId);
     */

    @Query("select sum(d.realDebtAmount) from Debt d where d.user.id= :userId and d.expiryDate<= :date")
    BigDecimal sumRealDebtByUserIdAndAndExpiryDateBefore(Long userId, Date date);


}
