package lab3;

public class ArrayAverage {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = 0;
        int n = arr.length;

        try {
            for (int i = 0; i < n; i++) {
                sum += arr[i];
            }
            double average = (double) sum / n;
            System.out.println("Среднее арифметическое: " + average);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: Индекс вышел за границы массива");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Элемент массива не является числом");
        }
    }
}

