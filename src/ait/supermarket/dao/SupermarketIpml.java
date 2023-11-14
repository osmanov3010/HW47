package ait.supermarket.dao;

import ait.supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SupermarketIpml implements Supermarket, Iterable<Product> {
    private Collection<Product> products;

    public SupermarketIpml() {
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
        return findProductsByPredicate(p -> p.getCategory().equals(category));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        return findProductsByPredicate(p -> p.getBrand().equals(brand));
    }

    @Override
    public Iterable<Product> findProductWithExpiredDate() {
        return findProductsByPredicate(p -> LocalDate.now().compareTo(p.getExpDate()) > 0);
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
