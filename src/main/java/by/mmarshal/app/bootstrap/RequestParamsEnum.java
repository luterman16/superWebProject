package by.mmarshal.app.bootstrap;

public enum RequestParamsEnum {
    LOGIN("username"),
    PASSWORD("password"),
    COMMAND("command"),
    ADDITEM("addItem"),
    ITEMS("items"),
    ITEMID("itemId"),
    TOTALPRICE("totalprice"),
    SHOPPING_CART_PRODUCTS("itemsIdinCart"),
    COUNT_CART_PRODUCTS("itemsInCort"),
    CATEGORY("categories");

    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
