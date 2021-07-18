package by.teachmeskills.eshop.Utils;

import by.teachmeskills.eshop.components.Cart;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static by.teachmeskills.eshop.RequestParamsEnum.SHOPPING_CART;

public class AddItemToCart {
    public static void addItemTocart (HttpSession HttpSession, int itemId){
        if (HttpSession.getAttribute(SHOPPING_CART.getValue()) != null &&
                !HttpSession.getAttribute(SHOPPING_CART.getValue()).toString().equals(0)){
            Cart cart = new Cart((ArrayList<Integer>) HttpSession.getAttribute(SHOPPING_CART.getValue()));
            cart.addProduct(itemId);
            HttpSession.setAttribute("cartProductsListCounter", cart.getProducts().size());
            HttpSession.setAttribute(SHOPPING_CART.getValue(), cart.getProducts());
        } else {
            Cart cart = new Cart();
            cart.addProduct(itemId);
            HttpSession.setAttribute("cartProductsListCounter", cart.getProducts().size());
            HttpSession.setAttribute(SHOPPING_CART.getValue(), cart.getProducts());
        }
    }
}
