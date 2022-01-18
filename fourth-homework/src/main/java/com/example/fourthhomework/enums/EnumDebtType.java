package com.example.fourthhomework.enums;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum EnumDebtType {

    LATE_FEE("lateFee"),
    NORMAL("normal");

    private String debtType;

    @Override
    public String toString() {
        return debtType;
    }

}
