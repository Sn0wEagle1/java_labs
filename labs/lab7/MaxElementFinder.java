package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MaxElementFinder implements Runnable {
    private int[][] matrix;
    private int startRow;
    private int endRow;
    private int maxElement;

    public MaxElementFinder(int[][] matrix, int startRow, int endRow) {
        this.matrix = matrix;
        this.startRow = startRow;
        this.endRow = endRow;
        this.maxElement = 0;
    }

    public int getMaxElement() {
        return maxElement;
    }

    @Override
    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;
        int numberOfThreads = 4;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        int batchSize = numberOfRows / numberOfThreads;
        int startRow = 0;

        MaxElementFinder[] finders = new MaxElementFinder[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            int endRow = startRow + batchSize;
            if (i == numberOfThreads - 1) {
                endRow = numberOfRows;
            }

            MaxElementFinder finder = new MaxElementFinder(matrix, startRow, endRow);
            finders[i] = finder;

            executorService.submit(finder);

            startRow = endRow;
        }

        // Ожидание завершения всех потоков
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Нахождение максимального элемента из результатов каждого потока
        int globalMax = 0;
        for (MaxElementFinder finder : finders) {
            int threadMax = finder.getMaxElement();
            if (threadMax > globalMax) {
                globalMax = threadMax;
            }
        }

        System.out.println("Max Element: " + globalMax);
    }
}
