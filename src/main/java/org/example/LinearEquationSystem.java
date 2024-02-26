package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class LinearEquationSystem {
    private final FractionMatrix matrix;
    private final int[] basisVariables;

    private LinearEquationSystem(FractionMatrix matrix, int[] basisVariables) {
        this.matrix = matrix;
        this.basisVariables = basisVariables;
    }

    public static LinearEquationSystem fromFile(BufferedReader reader) throws IOException {
        String[] dimensions = reader.readLine().split("\\s+");
        int m = Integer.parseInt(dimensions[0]);
        int n = Integer.parseInt(dimensions[1]);

        FractionMatrix matrix = new FractionMatrix(m, n);

        for (int i = 0; i < m; i++) {
            String[] values = reader.readLine().split("\\s+");
            for (int j = 0; j < n + 1; j++) {
                String[] fractionParts = values[j].split("/");
                int numerator = Integer.parseInt(fractionParts[0]);
                int denominator = fractionParts.length > 1 ? Integer.parseInt(fractionParts[1]) : 1;
                matrix.setElement(i, j, new Fraction(numerator, denominator));
            }
        }

        int[] basisVariables = new int[m];
        String[] basisLine = reader.readLine().split("\\s+");
        for (int i = 0; i < m; i++) {
            basisVariables[i] = Integer.parseInt(basisLine[i]) - 1;
        }

        return new LinearEquationSystem(matrix, basisVariables);
    }

    public Fraction[] solve() {
        return matrix.solve(basisVariables);
    }

    @Override
    public String toString() {
        return matrix.toString();
    }
}
