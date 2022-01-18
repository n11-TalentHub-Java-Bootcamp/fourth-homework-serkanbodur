package com.example.fourthhomework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection implements Serializable {

    @Nullable
    @SequenceGenerator(name = "generator", sequenceName = "COLLECTION_ID_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "collection_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date collectionDate;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<Debt> debts;

}
