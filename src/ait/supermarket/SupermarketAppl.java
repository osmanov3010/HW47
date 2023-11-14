package ait.supermarket;

import ait.supermarket.dao.Supermarket;
import ait.supermarket.dao.SupermarketImpl;
import ait.supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SupermarketAppl {
    public static void main(String[] args) {

        SupermarketImpl supermarket = new SupermarketImpl();

        Product p1 = new Product(1, "Product 1", "Category 1", "Brand 1", 10.10, LocalDate.of(2023, 12, 28));
        Product p2 = new Product(2, "Product 2", "Category 2", "Brand 3", 12.19, LocalDate.of(2023, 12, 1));
        Product p3 = new Product(3, "Product 3", "Category 3", "Brand 1", 100, LocalDate.of(2024, 8, 5));
        Product p4 = new Product(4, "Product 4", "Category 1", "Brand 5", 100.16, LocalDate.of(2023, 10, 7));

        supermarket.addProduct(p1);
        supermarket.addProduct(p2);
        supermarket.addProduct(p3);
        supermarket.addProduct(p4);

        printAllProducts(supermarket.iterator(), "All products");
        printAllProducts(supermarket.findProductWithExpiredDate().iterator(), "Expired products");

    }

    public static void printAllProducts(Iterator<Product> iterator, String title) {
        System.out.println("===== " + title + "====");
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

}
