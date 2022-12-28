package com.example.spock;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    public static long calculate(long amount, float rate, RoundingMode roundingMode) {
        if (amount < 0) {
            throw new IllegalStateException("음수는 계산할 수 없습니다.");
        }

        return BigDecimal.valueOf(amount * rate * 0.01)
            .setScale(0, roundingMode)
            .longValue();
    }
}
