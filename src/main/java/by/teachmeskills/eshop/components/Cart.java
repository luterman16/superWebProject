package by.teachmeskills.eshop.components;

import by.teachmeskills.eshop.repository.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private ArrayList<Integer> products;
    private int totalPrice = 0;

    public Cart(ArrayList<Integer> products) {
        this.products = products;
    }

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Integer id) {
        this.products.add(id);
    }

    public void removeProduct(int productId) {
        int product = products.indexOf(productId);
        products.remove(product);
    }

    public ArrayList<Integer> getProducts() {
        return products;
    }

    public int getTotalPrice(List<Product> products) {
        for (Product p : products) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public void clear() {
        products.clear();
    }

}
