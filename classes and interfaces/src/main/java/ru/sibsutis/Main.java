package ru.sibsutis;

public class Main {
    public static void main(String []args) {
        Developer.fillCSV();
        Manager.fillCSV();

        Developer me = new Developer("Kirill", "I-zey", "C++");
        Manager wellKnown = new Manager("???", "Unknown", "348760543256");

        me.toCSV();
        me.fromCSV("");
        wellKnown.toCSV();
    }
}