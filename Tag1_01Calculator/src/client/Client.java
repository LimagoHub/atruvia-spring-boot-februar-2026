package client;

import math.Calculator;


public class Client {

    private final Calculator calculator;

    public Client(final Calculator calculator) {
        this.calculator = calculator;
    }

    public void go() {
        System.out.println(calculator.add(1, 2));
    }
}
