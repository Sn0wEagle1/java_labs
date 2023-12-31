package lab7;

import java.util.concurrent.Semaphore;

class Warehouse {
    private final Semaphore accessSemaphore = new Semaphore(1); // Семафор для доступа к складу
    private final Semaphore weightSemaphore = new Semaphore(3); // Семафор для контроля над весом товаров
    private int currentWeight = 0;

    public void loadGoods(int weight) throws InterruptedException {
        weightSemaphore.acquire(); // Получаем разрешение на контроль веса товаров
        accessSemaphore.acquire(); // Получаем разрешение на доступ к складу

        if (currentWeight + weight > 150) {
            System.out.println("Общий вес товаров на складе: " + currentWeight + " кг. Грузчики отправились на другой склад.");
            currentWeight = 0;
        }

        currentWeight += weight;
        System.out.println("Грузчик переносит товар весом " + weight + " кг. Общий вес: " + currentWeight + " кг");

        accessSemaphore.release(); // Освобождаем разрешение на доступ к складу
        weightSemaphore.release(); // Освобождаем разрешение на контроль веса товаров
    }

    public int getCurrentWeight() {
        return currentWeight;
    }
}

class Loader implements Runnable {
    private final Warehouse warehouse;
    private final int[] weights;
    private int currentIndex = 0;

    public Loader(Warehouse warehouse, int[] weights) {
        this.warehouse = warehouse;
        this.weights = weights;
    }

    @Override
    public void run() {
        try {
            while (currentIndex < weights.length) {
                int weight = weights[currentIndex];
                currentIndex += 1;
                warehouse.loadGoods(weight);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        int[] weights = {50, 70, 40, 60, 80};

        Thread loader1 = new Thread(new Loader(warehouse, weights));

        loader1.start();
    }
}