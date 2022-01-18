package com.example.fourthhomework.entity;

import com.example.fourthhomework.enums.EnumDebtType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @Column(name = "main_debt_amount", scale=2, precision = 15)
    private BigDecimal mainDebtAmount;

    @Column(name = "real_debt_amount", scale=2, precision = 15)
    private BigDecimal realDebtAmount;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Transient
    private BigDecimal lateFee;

    @Column(name = "debt_type")
    private EnumDebtType debtType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "collection_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_DEBT_COLLECTION_ID")
    )
    private Collection collection;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "FK_DEBT_USER_ID")
    )
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MAIN_DEBT")
    private Debt mainDebt;

}
