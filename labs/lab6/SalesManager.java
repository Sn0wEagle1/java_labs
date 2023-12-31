package lab6;

import java.util.*;

class SaleItem implements Comparable<SaleItem> {
    private String productName;
    private double price;
    private int quantitySold;

    public SaleItem(String productName, double price, int quantitySold) {
        this.productName = productName;
        this.price = price;
        this.quantitySold = quantitySold;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    @Override
    public int compareTo(SaleItem other) {
        // Сравнение по количеству проданных товаров в убывающем порядке
        return Integer.compare(other.quantitySold, this.quantitySold);
    }

    @Override
    public String toString() {
        return "Product: " + productName + ", Price: " + price + ", Quantity Sold: " + quantitySold;
    }
}

public class SalesManager {
    private TreeSet<SaleItem> sales;

    public SalesManager() {
        this.sales = new TreeSet<>();
    }

    public void addSaleItem(SaleItem saleItem) {
        sales.add(saleItem);
    }

    public void displaySales() {
        System.out.println("List of sold items:");
        Iterator<SaleItem> iterator = sales.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public double calculateTotalSales() {
        double totalSales = 0;
        Iterator<SaleItem> iterator = sales.iterator();
        while (iterator.hasNext()) {
            SaleItem item = iterator.next();
            totalSales += item.getPrice() * item.getQuantitySold();
        }
        return totalSales;
    }

    public SaleItem getMostPopularItem() {
        if (sales.isEmpty()) {
            return null;
        }
        return sales.first();  // Получение первого элемента (наиболее продаваемого товара)
    }

    public static void main(String[] args) {
        SalesManager manager = new SalesManager();

        manager.addSaleItem(new SaleItem("Laptop", 1200.0, 10));
        manager.addSaleItem(new SaleItem("Smartphone", 800.0, 20));
        manager.addSaleItem(new SaleItem("Tablet", 500.0, 15));

        manager.displaySales();
        System.out.println("Total Sales: $" + manager.calculateTotalSales());

        SaleItem mostPopularItem = manager.getMostPopularItem();
        System.out.println("Most Popular Item: " + (mostPopularItem != null ? mostPopularItem.getProductName() : "N/A"));
    }
}
