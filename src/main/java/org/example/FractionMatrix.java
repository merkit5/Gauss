package org.example;

public class FractionMatrix {
    private final Fraction[][] elements;

    public FractionMatrix(int m, int n) {
        elements = new Fraction[m][n + 1];
    }

    public void setElement(int i, int j, Fraction value) {
        elements[i][j] = value;
    }

    public Fraction[] solve(int[] basisVariables) {
        int m = elements.length;
        int n = elements[0].length - 1;

        for (int i = 0; i < m; i++) {
            int pivot = basisVariables[i];
            Fraction pivotValue = elements[i][pivot];

            for (int j = 0; j <= n; j++) {
                elements[i][j] = elements[i][j].divide(pivotValue);
            }

            for (int k = 0; k < m; k++) {
                if (k != i) {
                    Fraction factor = elements[k][pivot];
                    for (int j = 0; j <= n; j++) {
                        elements[k][j] = elements[k][j].subtract(elements[i][j].multiply(factor));
                    }
                }
            }
        }

        Fraction[] solution = new Fraction[n];
        for (int i = 0; i < m; i++) {
            int pivot = basisVariables[i];
            solution[pivot] = elements[i][n];
        }

        return solution;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Fraction[] row : elements) {
            sb.append("[");
            for (Fraction element : row) {
                sb.append(String.format("%5s", element)).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            sb.append("\n");
        }
        return sb.toString();
    }

}
