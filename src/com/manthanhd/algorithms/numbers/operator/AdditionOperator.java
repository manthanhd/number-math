package com.manthanhd.algorithms.numbers.operator;

import com.manthanhd.algorithms.numbers.Number;

/**
 * Created by manthanhd on 10/05/2016.
 */
public class AdditionOperator implements Operator {
    @Override
    public Number performOperation(Number num1, Number num2) {
        return new Number(num1.getValue() + num2.getValue(), num1, num2, this);
    }
}
