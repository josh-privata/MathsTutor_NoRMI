package server;

import java.security.SecureRandom;
import java.util.*;

/**
 * @filename MathsTutor.java
 *
 * @application MathsTutor_Server
 *
 * @author Mary Tom created 28th February 2016
 *
 * @description This program creates a MathsTutor class to generate double digit
 * arithmetic problems and the main method demonstrates the use by looping
 * through generating and testing five problems for a chosen arithmetic
 * operation.
 *
 * @modified Josh Cannons April 9th 2016. Ammendments allow for inclusion into
 * MathsTutor_Server.Server.class to provide simplified client/server
 * functionality
 */
public class MathsTutor {

    // User Variables
    private static final int MIN = 10;
    private static final int MAX = 100;
    private static final int NUMBEROFQUESTIONS = 10;
    private static final String MENU = "Please choose from the selection below#"
            + "Enter 'a' or 'A' for Addition#"
            + "Enter 's' or 'S' for Subtraction#"
            + "Enter 'm' or 'M' for Multiplication#"
            + "Enter 'd' or 'D' for Division#"
            + "Enter 'q' or 'Q' to Quit the program#"
            + "Please make your selection : ";

    // System Variables
    private int valueOne, valueTwo, right;
    private char operation, symbol;
    private final SecureRandom rand;
    private String result;

    // Constructor allocates memory for securerandom and initiates right to 0;     
    public MathsTutor() {
        rand = new SecureRandom();
        right = 0;
    }

    // Simple getters
    public int getNumberOfQuestions() {
        return NUMBEROFQUESTIONS;
    }

    public String getMenu() {
        return MENU;
    }

    public int getRight() {
        return right;
    }

    public String getResult() {
        return result;
    }

    // Simple setters
    private void setValueOne(int one) {
        this.valueOne = one;
    }

    private void setValueTwo(int two) {
        this.valueTwo = two;
    }

    // Method to set the mathematial operation 
    public void setOperation(String op) {
        op.trim();
        char ch = op.charAt(0);
        System.out.println(op);
        symbol = '+';
        switch (ch) {
            case 'A' | 'a':
                symbol = '+';
                break;
            case 'S' | 's':
                symbol = '-';
                break;
            case 'M' | 'm':
                symbol = '*';
                break;
            case 'D' | 'd':
                symbol = '/';
                break;
        }
        this.operation = symbol;
    }

    // This method sets a problem with two random numbers.
    public String setProblem() {
        Date today = new Date();
        rand.setSeed(today.getTime());
        setValueOne(MIN + rand.nextInt(MAX - MIN));
        setValueTwo(MIN + rand.nextInt(MAX - MIN));
        return String.format("%d %s %d = ", valueOne, symbol, valueTwo);
    }

    // This method return 'correct' or 'incorrect' based on the answer.
    public String checkAnswer(int answer) {
        result = " incorrect";
        switch (operation) {
            case '+':
                if (valueOne + valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
            case '-':
                if (valueOne - valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
            case '*':
                if (valueOne / valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
            case '/':
                if (valueOne * valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
        }
        return result;
    }
}
