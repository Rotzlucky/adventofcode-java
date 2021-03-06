package aoc2018;

import aoc.Day;

import java.util.Arrays;
import java.util.List;

public class Day3 extends Day {
    public Day3(String year, String day) {
        super(year, day);
    }

    @Override
    protected void part1(List<String> inputs) {
        int[][] matrix = getFilledMatrix(inputs);

        long count = Arrays.stream(matrix).flatMapToInt(x -> Arrays.stream(x)).filter(value -> value >= 2).count();

        printSolution(1, (int) count);
    }

    private int[][] getFilledMatrix(List<String> inputs) {
        int[][] matrix = new int[1000][1000];

        for (String input : inputs) {
            String[] split = input.split("#| @ |,|: |x");
            List<String> stringValues = Arrays.asList(split).subList(1, split.length);

            int[] values = stringValues.stream().mapToInt(Integer::parseInt).toArray();

            for (int i = values[1]; i < values[1] + values[3]; i++) {
                for (int j = values[2]; j < values[2] + values[4]; j++) {
                    matrix[i][j] += 1;
                }
            }
        }
        return matrix;
    }

    @Override
    protected void part2(List<String> inputs) {
        int[][] filledMatrix = getFilledMatrix(inputs);

        int id = 0;

        for (String input : inputs) {
            String[] split = input.split("#| @ |,|: |x");
            List<String> stringValues = Arrays.asList(split).subList(1, split.length);

            int[] values = stringValues.stream().mapToInt(Integer::parseInt).toArray();

            boolean overlap = false;

            outerLoop:
            for (int i = values[1]; i < values[1] + values[3]; i++) {
                for (int j = values[2]; j < values[2] + values[4]; j++) {
                    if (filledMatrix[i][j] > 1) {
                        overlap = true;
                        break outerLoop;
                    }
                }
            }

            if (!overlap) {
                id = values[0];
            }
        }

        printSolution(2, id);
    }
}
