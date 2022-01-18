package com.example.fourthhomework.repository;

import com.example.fourthhomework.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findAllByCollectionDateBetween(Date startDate, Date endDate);

    @Query("Select c from Collection c join c.debts d where d.user.id= :userId")
    List<Collection> findAllByUserId(Long userId);

    @Query("select sum(d.mainDebtAmount) from Collection c join c.debts d where d.user.id= :userId and d.debtType= 0")
    BigDecimal sumMainDebtAmountByUserId(Long userId);

}
