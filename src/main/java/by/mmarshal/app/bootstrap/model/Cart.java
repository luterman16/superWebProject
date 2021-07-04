package by.mmarshal.app.bootstrap.model;

import java.util.ArrayList;

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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void clear() {
        products.clear();
    }

}
