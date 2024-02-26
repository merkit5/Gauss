package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            LinearEquationSystem system = LinearEquationSystem.fromFile(reader);
            Fraction[] solution = system.solve();
            System.out.println("Решение системы:");
            System.out.println(system);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
