import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StoreSales {
    private Set<String> uniqueProducts;
    private Map<String, Integer> productSales;
    private int totalSales;
    private String mostPopularProduct;
    // uniqueProducts: множество для хранения уникальных продуктов.
//productSales: хэш-карта для хранения количества проданных единиц каждого продукта.
//totalSales: общая сумма продаж.
//mostPopularProduct: наиболее популярный продукт.
    public StoreSales() {
        uniqueProducts = new HashSet<>();
        productSales = new HashMap<>();
        totalSales = 0;
        mostPopularProduct = null;
    }
    // Конструктор инициализирует переменные.

    // Этот метод добавляет продажу в статистику. Он обновляет множество уникальных продуктов, общую сумму продаж,
    // количество продаж каждого продукта и наиболее популярный продукт.
    public void addSale(String product, int price) {
        uniqueProducts.add(product);
        totalSales += price;

        productSales.put(product, productSales.getOrDefault(product, 0) + 1);

        if (mostPopularProduct == null ||
                productSales.get(product) > productSales.get(mostPopularProduct)) {
            mostPopularProduct = product;
        }
    }

    public void displaySales() {
        System.out.println("Sold products:");
        for (String product : uniqueProducts) {
            System.out.println(product);
        }

        System.out.println("Total price for sale: £" + totalSales);

        System.out.println("The most popular product on the list: " + mostPopularProduct);
    } // Этот метод выводит текущую статистику продаж.

    public static void main(String[] args) {
        StoreSales store = new StoreSales();
        store.addSale("Iphone", 1599);
        store.addSale("IMac", 1000);
        store.addSale("Iphone", 1599);
        store.addSale("IPad", 1499);
        store.addSale("IMac", 1000);

        store.displaySales();
    }
}
//Методы:
//- addSale(String product, int price) — добавляет проданный товар и его цену.
//- displaySales() — выводит список уникальных проданных товаров, общую сумму продаж и наиболее популярный товар.