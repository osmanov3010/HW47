package ait.supermarket.dao;

import ait.supermarket.model.Product;

public interface Supermarket extends Iterable<Product> {
    boolean addProduct(Product product);

    Product removeProduct(long barCode);

    Product findByBarCode(long barCode);

    Iterable<Product> findByCategory(String category);

    Iterable<Product> findByBrand(String brand);

    Iterable<Product> findProductWithExpiredDate();

    int skuQuantity();
}
