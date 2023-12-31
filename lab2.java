abstract class Transport {
    private static int objectCount = 0;
    private String brand;
    private int year;
    private double price;

    public Transport() {
        this.brand = "Неизвестно";
        this.year = 0;
        this.price = 0.0;
        objectCount++;
    }

    public Transport(String brand, int year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
        objectCount++;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getObjectCount() {
        return objectCount;
    }

    public abstract void move();

    public void displayInfo() {
        System.out.println("Бренд: " + brand);
        System.out.println("Год выпуска: " + year);
        System.out.println("Цена: " + price);
    }
}

class Car extends Transport {
    private int passengerCapacity;

    public Car() {
        super();
        this.passengerCapacity = 0;
    }

    public Car(String brand, int year, double price, int passengerCapacity) {
        super(brand, year, price);
        this.passengerCapacity = passengerCapacity;
    }


    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Вместимость: " + passengerCapacity + " пассажиров" );
    }

    @Override
    public void move() {
        System.out.println("Легковой автомобиль двигается по дороге");
    }
}

class Truck extends Transport {
    private double cargoCapacity;

    public Truck() {
        super();
        this.cargoCapacity = 0.0;
    }

    public Truck(String brand, int year, double price, double cargoCapacity) {
        super(brand, year, price);
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Грузоподъемность: " + cargoCapacity + " тонн");
    }

    @Override
    public void move() {
        System.out.println("Грузовой автомобиль двигается по дороге с грузом");
    }
}

class Motorcycle extends Transport {
    private boolean hasHelmet;

    public Motorcycle() {
        super();
        this.hasHelmet = false;
    }

    public Motorcycle(String brand, int year, double price, boolean hasHelmet) {
        super(brand, year, price);
        this.hasHelmet = hasHelmet;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Наличие шлема: " + hasHelmet );
    }

    public void move(int speed) {
        System.out.println("Мотоцикл двигается со скоростью " + speed + " км/ч");
    }

    @Override
    public void move() {
        System.out.println("Мотоцикл двигается по дороге");
    }
}

public class lab2 {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2020, 25000.0, 5);
        Truck truck = new Truck("Volvo", 2018, 75000.0, 10.0);
        Motorcycle motorcycle = new Motorcycle("Harley-Davidson", 2021, 15000.0, true);

        car.displayInfo();
        car.move();

        truck.displayInfo();
        truck.move();

        motorcycle.displayInfo();
        motorcycle.move(80);

        int count = Transport.getObjectCount();
        System.out.println("Количество созданных объектов: " + count);
    }
}

