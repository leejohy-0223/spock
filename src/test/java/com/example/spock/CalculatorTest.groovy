package com.example.spock

import spock.lang.Specification

import java.math.RoundingMode

class CalculatorTest extends Specification {

    def "금액의 퍼센트 계산 결과값의 소수점 버림을 검증한다"() {

        given:
        RoundingMode 소수점버림 = RoundingMode.DOWN

        when:
        def result = Calculator.calculate(10000L, 0.1f, 소수점버림)

        then:
        result == 10L
    }

    def "여러 금액의 퍼센트 계산 결과값의 소수점 버림을 검증한다"() {

        given:
        RoundingMode 소수점버림 = RoundingMode.DOWN

        expect:
        Calculator.calculate(amount, rate, 소수점버림) == result

        where:
        amount | rate  | result
        10000L | 0.1f  | 10L
        2799L  | 0.2f  | 5L
        159L   | 0.15f | 0L
        2299L  | 0.15f | 3L
    }

    def "음수가 들어오면 예외가 발생한다"() {

        given:
        RoundingMode 소수점버림 = RoundingMode.DOWN

        when:
        Calculator.calculate(-10000L, 0.1f, 소수점버림)

        then:
        def e = thrown(IllegalStateException.class)
        e.message == "음수는 계산할 수 없습니다."
    }

    def "주문금액의 소수점 버림을 검증한다"() {

        given:
        RoundingMode 소수점버림 = RoundingMode.DOWN
        def mockAmountService = Mock(AmountService.class)

        when:
        long amount = mockAmountService.getAmount()

        then:
        mockAmountService.getAmount() >> 10000L
        amount == 10000L
        10L == Calculator.calculate(amount, 0.1f, 소수점버림)
    }

}
