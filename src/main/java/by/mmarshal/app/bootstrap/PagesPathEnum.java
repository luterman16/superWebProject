package by.mmarshal.app.bootstrap;

public enum PagesPathEnum {
    HOME_PAGE("home.jsp"),
    SIGN_IN_PAGE("signin.jsp"),
    ITEM_PAGE("item.jsp"),
    CART_PAGE("cart.jsp"),
    CATEGORIES("categories.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
