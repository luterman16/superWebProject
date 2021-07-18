package by.teachmeskills.eshop;

public enum RequestParamsEnum {
    SHOPPING_CART("cart"),
    TOTAL_PRICE("totalprice"),
    SHOPPING_CART_COUNTER("cartProductsListCounter"),
    POPULAR_CATEGORIES_LIST_REQ_PARAM("categories"),
    CATEGORY_PARAM("category"),
    ITEMS("item");

    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
