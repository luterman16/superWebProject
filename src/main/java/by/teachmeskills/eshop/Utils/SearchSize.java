package by.teachmeskills.eshop.Utils;

import javax.servlet.http.HttpSession;

public class SearchSize {
    public static void searchSize(HttpSession HttpSession) {

        String pageSize = (HttpSession.getAttribute("pageSize")) != (null) ?
                HttpSession.getAttribute("pageSize").toString() : "5";

        HttpSession.setAttribute("pageSize", pageSize);

    }

    public static void searchSize(HttpSession HttpSession, int pageSize) {
        HttpSession.setAttribute("pageSize", pageSize);
    }
}
