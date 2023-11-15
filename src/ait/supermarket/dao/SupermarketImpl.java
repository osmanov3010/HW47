package ait.supermarket.dao;

import ait.supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SupermarketImpl implements Supermarket {
    private Collection<Product> products;
    //private Collection<Product> products = new ArrayList<>();  - можно сделать так. Тогда конструктор не нужно создавать

    public SupermarketImpl() {
        products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null || findByBarCode(product.getBarCode()) != null) {
            return false;
        }

        return products.add(product);
    }

    @Override
    public Product removeProduct(long barCode) {
        Product removingProduct = findByBarCode(barCode);
        products.remove(removingProduct);
        return removingProduct;
    }

    @Override
    public Product findByBarCode(long barCode) {
        for (Product p : products) {
            if (p.getBarCode() == barCode) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        return findProductsByPredicate(p -> p.getCategory().equalsIgnoreCase(category));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        return findProductsByPredicate(p -> p.getBrand().equalsIgnoreCase(brand));
    }

    @Override
    public Iterable<Product> findProductWithExpiredDate() {
        LocalDate currentTime = LocalDate.now();
        return findProductsByPredicate(p -> currentTime.isAfter(p.getExpDate()));
    }

    @Override
    public int skuQuantity() {
        return products.size();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    private List<Product> findProductsByPredicate(Predicate<Product> predicate) {

        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (predicate.test(p)) {
                result.add(p);
            }
        }

        return result;
    }
}
