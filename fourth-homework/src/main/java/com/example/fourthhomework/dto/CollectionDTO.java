package com.example.fourthhomework.dto;

import com.example.fourthhomework.entity.Debt;
import com.example.fourthhomework.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class CollectionDTO {
    private Long id;
    private Date collectionDate;
    private Long debtId;
}
