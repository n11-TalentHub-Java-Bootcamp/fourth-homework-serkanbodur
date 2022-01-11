package com.example.fourthhomework.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumDebtType {

    LATE_FEE("lateFee"),
    NORMAL("normal");

    private String type;

}
