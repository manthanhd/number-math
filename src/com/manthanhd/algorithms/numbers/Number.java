package com.manthanhd.algorithms.numbers;

import com.manthanhd.algorithms.numbers.operator.Operator;

/**
 * Created by manthanhd on 10/05/2016.
 */
public class Number {
    private double value;
    private Number operand1;
    private Number operand2;
    private Operator operator;

    public Number(double value, Number operand1, Number operand2, Operator operator) {
        this.value = value;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    public Number(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Number getOperand1() {
        return operand1;
    }

    public Number getOperand2() {
        return operand2;
    }

    public Operator getOperator() {
        return operator;
    }
}
