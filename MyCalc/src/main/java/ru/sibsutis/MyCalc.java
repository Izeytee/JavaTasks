package ru.sibsutis;

import java.util.Scanner;

class MyCalc {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Integer first, second, result;
        String op;

        first = scan.nextInt();
        op = scan.next();
        second = scan.nextInt();

        switch (op) {
            case("+"):
                result = first + second;
                break;
            case("-"):
                result = first - second;
                break;
            case("*"):
                result = first * second;
                break;
            case("/"):
                result = first / second;
                break;
            default:
                System.out.println("Error");
                return;
        }

        System.out.print(result);
    }
}