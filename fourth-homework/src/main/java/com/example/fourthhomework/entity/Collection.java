package com.example.fourthhomework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection implements Serializable {

    @Id
    private Long id;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "collection_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectionDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "debt_id", referencedColumnName = "id")
    private Debt debt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_COLLECTION_USER_ID")
    )
    private User user;
}
