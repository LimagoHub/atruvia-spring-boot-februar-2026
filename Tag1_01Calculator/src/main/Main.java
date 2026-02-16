package main;

import client.Client;
import math.Calculator;
import math.CalculatorImpl;
import math.CalculatorLogger;
import math.CalculatorSecure;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator = new CalculatorLogger(calculator);
        calculator = new CalculatorSecure(calculator);
        Client client = new Client(calculator);
        client.go();
    }
}
