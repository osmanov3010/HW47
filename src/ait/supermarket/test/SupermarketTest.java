package ait.supermarket.test;

import ait.supermarket.dao.Supermarket;
import ait.supermarket.dao.SupermarketImpl;
import ait.supermarket.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketTest {

    Supermarket supermarket;
    Product[] products;

    @BeforeEach
    void setUp() {
        supermarket = new SupermarketImpl();

        products = new Product[]{
                new Product(1, "Product 1", "Category 1", "Brand 1", 10.10, LocalDate.of(2023, 12, 28)),
                new Product(2, "Product 2", "Category 2", "Brand 3", 12.19, LocalDate.of(2023, 12, 1)),
                new Product(3, "Product 3", "Category 3", "Brand 1", 100, LocalDate.of(2024, 8, 5)),
                new Product(4, "Product 4", "Category 1", "Brand 5", 100.16, LocalDate.of(2023, 10, 7)),
                new Product(5, "Product 5", "Category 2", "Brand 3", 300.10, LocalDate.of(2023, 7, 22)),
                new Product(6, "Product 6", "Category 3", "Brand 7", 250.10, LocalDate.of(2024, 11, 22)),
                new Product(7, "Product 7", "Category 4", "Brand 3", 10.90, LocalDate.of(2024, 8, 22)),
                new Product(8, "Product 8", "Category 3", "Brand 4", 89.99, LocalDate.of(2023, 9, 22)),
                new Product(9, "Product 9", "Category 2", "Brand 6", 2.10, LocalDate.of(2025, 6, 22)),
                new Product(10, "Product 10", "Category 1", "Brand 8", 1.89, LocalDate.of(2024, 5, 22)),
        };

        for (Product p : products) {
            supermarket.addProduct(p);
        }
    }

    @Test
    void addProduct() {
        assertFalse(supermarket.addProduct(null));
        Product p = new Product(11, "Product 11", "Category 8", "Brand 11", 10.10, LocalDate.of(2023, 12, 28));
        assertTrue(supermarket.addProduct(p));
        assertFalse(supermarket.addProduct(p));
        assertEquals(11, supermarket.skuQuantity());
        assertEquals(p, supermarket.findByBarCode(11));
    }

    @Test
    void removeProduct() {
        assertEquals(products[0], supermarket.removeProduct(1));
        assertEquals(null, supermarket.removeProduct(12));
    }

    @Test
    void findByBarCode() {
        assertEquals(products[0], supermarket.findByBarCode(1));
        assertEquals(products[7], supermarket.findByBarCode(8));
        assertEquals(null, supermarket.findByBarCode(12));
    }

    @Test
    void findByCategory() {
        List<Product> expected = new ArrayList<>();
        expected.add(products[0]);
        expected.add(products[3]);
        expected.add(products[9]);

        assertEquals(expected, supermarket.findByCategory("Category 1"));

    }

    @Test
    void findByBrand() {
        List<Product> expected = new ArrayList<>();
        expected.add(products[1]);
        expected.add(products[4]);
        expected.add(products[6]);

        assertEquals(expected, supermarket.findByBrand("Brand 3"));
    }

    @Test
    void findProductWithExpiredDate() {
        List<Product> expected = new ArrayList<>();
        expected.add(products[3]);
        expected.add(products[4]);
        expected.add(products[7]);

        assertEquals(expected, supermarket.findProductWithExpiredDate());
    }

    @Test
    void skuQuantity() {
        assertEquals(10, supermarket.skuQuantity());
    }
}