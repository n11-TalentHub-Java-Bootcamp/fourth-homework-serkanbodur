package com.example.fourthhomework.entity;

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
public class LateFeeRate implements Serializable {

    @Id
    private Long id;

    @Column(name = "first_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstDate;

    @Column(name = "last_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDate;

    @Column(name = "rate", precision = 2)
    private BigDecimal rate;
}
