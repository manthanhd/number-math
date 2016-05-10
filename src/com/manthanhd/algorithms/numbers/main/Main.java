package com.manthanhd.algorithms.numbers.main;

import com.manthanhd.algorithms.numbers.Number;
import com.manthanhd.algorithms.numbers.operator.*;

import java.util.Stack;

public class Main {

    final static double target = 24;
    static Number answer;
    static double diff = Double.MAX_VALUE;
    private static double attempts;

    public static void main(String[] args) {
        final Number[] numbers = new Number[]{new Number(8), new Number(8), new Number(3), new Number(3)};
        final Number[] shuffled = shuffle(numbers);
        final Number[][] numMatrix = new Number[4][4];
        numMatrix[0] = new Number[]{new Number(8), new Number(8), new Number(3), new Number(3)};
        numMatrix[1] = new Number[]{new Number(8), new Number(3), new Number(8), new Number(3)};
        numMatrix[2] = new Number[]{new Number(3), new Number(3), new Number(8), new Number(8)};
        numMatrix[3] = new Number[]{new Number(3), new Number(8), new Number(3), new Number(8)};

        final Operator[] operators = new Operator[]{new AdditionOperator(), new MultiplicationOperator(), new SubtractionOperator(), new DivisionOperator()};
        attempts = 0;
        for (int i = 0; i < 4; i++) {
            final Number[] numArray = numMatrix[i];
            for (Operator operator1 : operators) {
                for (Operator operator2 : operators) {
                    for (Operator operator3 : operators) {
                        final Number result = compute(numArray, operator1, operator2, operator3);
                        final double myDiff = Math.abs(target - result.getValue());
                        attempts++;
                        if (answer == null || diff > myDiff) {
                            System.out.println("------------------------------------------------------------");
                            System.out.println(String.format("Current Answer: %f", result.getValue()));
                            System.out.println(String.format("| 24 - %f | = %f", result.getValue(), myDiff));
                            System.out.println(String.format("Got this at attempt %s", attempts));
                            answer = result;
                            diff = myDiff;
                        }
                    }
                }
            }
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Made attempts: " + attempts);
    }

    private static Number compute(Number[] numArray, Operator operator1, Operator operator2, Operator operator3) {
        final Operator[] operators = new Operator[]{operator1, operator2, operator3};

        double target = 24;
        double minDiff = Double.MAX_VALUE;
        Number res = null;
        for(Operator op1 : operators) {
            for(Operator op2 : operators) {
                for(Operator op3 : operators) {
                    final Stack<Number> numberStack = new Stack<>();
                    final Stack<Operator> operatorStack = new Stack<>();
                    operatorStack.push(op1);
                    operatorStack.push(op2);
                    operatorStack.push(op3);
                    for (Number number : numArray) {

                        numberStack.push(number);
                        if (numberStack.size() == 2) {
                            final Number number1 = numberStack.pop();
                            final Number number2 = numberStack.pop();
                            final Operator operator = operatorStack.pop();
                            final Number result = operator.performOperation(number1, number2);
                            numberStack.push(result);
                        }
                    }
                    final Number result = numberStack.pop();
                    double df = Math.abs(result.getValue() - target);
                    if(df < minDiff) {
                        minDiff = df;
                        res = result;
                    }
                    attempts++;
                }
            }
        }


        return res;
    }

    private static Number[] shuffle(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int randomIndex = (int) (Math.random() * numbers.length);
            final Number currentNumber = numbers[i];
            final Number randomNumber = numbers[randomIndex];
            numbers[randomIndex] = currentNumber;
            numbers[i] = randomNumber;
        }
        return numbers;
    }
}
