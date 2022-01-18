package com.example.fourthhomework.dto;

import com.example.fourthhomework.enums.EnumDebtType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DebtDTO {
    private Long id;
    private BigDecimal mainDebtAmount;
    private BigDecimal realDebtAmount;
    private Date createdDate;
    private Date expiryDate;
    private EnumDebtType debtType;
    private Long userId;
    private Long mainDebtId;
    private Long collectionId;
}
