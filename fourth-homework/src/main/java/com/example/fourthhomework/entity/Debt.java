package com.example.fourthhomework.entity;

import com.example.fourthhomework.enums.EnumDebtType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "debt")
public class Debt implements Serializable {

    @SequenceGenerator(name = "generator", sequenceName = "DEBT_ID_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "main_debt_amount", nullable = false, precision = 2)
    private BigDecimal mainDebtAmount;

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Transient
    private BigDecimal lateFee;

    @Column(name = "debt_type")
    private EnumDebtType debtType;

    @OneToOne(mappedBy = "debt")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_DEBT_USER_ID")
    )
    private User user;
}
