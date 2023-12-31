package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArraySumCalculator implements Runnable {
    private int[] array;
    private int start;
    private int end;
    private int partialSum;

    public ArraySumCalculator(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // Получение частичной суммы
    public int getPartialSum() {
        return partialSum;
    }

    // Метод, выполняемый в отдельном потоке
    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numberOfThreads = 4;

        // Создание пула потоков
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        int batchSize = array.length / numberOfThreads;
        int start = 0;

        // Создание массива калькуляторов
        ArraySumCalculator[] calculators = new ArraySumCalculator[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            int end = start + batchSize;
            System.out.println(end);
            if (i == numberOfThreads - 1) {
                end = array.length;
            }

            // Создание калькулятора для каждого участка массива
            ArraySumCalculator calculator = new ArraySumCalculator(array, start, end);
            calculators[i] = calculator;

            // Запуск калькулятора в пуле потоков
            executorService.submit(calculator);

            start = end;
        }

        // Ожидание завершения всех потоков
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Суммирование результатов из каждого потока
        int totalSum = 0;
        for (ArraySumCalculator calculator : calculators) {
            totalSum += calculator.getPartialSum();
        }

        System.out.println("Total Sum: " + totalSum);
    }
}
