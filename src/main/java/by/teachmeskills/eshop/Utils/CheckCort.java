package by.teachmeskills.eshop.Utils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static by.teachmeskills.eshop.RequestParamsEnum.*;

public class CheckCort {
    public static void checkCort(HttpSession HttpSession) {
        Integer countSession;

        if ((ArrayList<Integer>) HttpSession.getAttribute(SHOPPING_CART.getValue()) == null) {
            countSession = 0;
        } else {
            countSession = ((ArrayList<Integer>) HttpSession.getAttribute(SHOPPING_CART.getValue())).size();
        }
        HttpSession.setAttribute(SHOPPING_CART_COUNTER.getValue(), countSession);
    }


}
