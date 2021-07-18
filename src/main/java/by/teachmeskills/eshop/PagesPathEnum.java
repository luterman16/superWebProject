package by.teachmeskills.eshop;

public enum PagesPathEnum {
    START_PAGE("categories"),
    SIGN_IN_PAGE("signin"),
    CATEGORY_PAGE("categories"),
    CATEGORY_LISTING_PAGE("category_listing"),
    CART_PAGE("cart"),
    REGISTRATION("registration"),
    PERSONAL_INFO("personalinfo"),
    FILTER_PAGE("filteritem"),
    AUTH_FOR_CONFIRM_PAY_PAGE("auth_for_confirm"),
    PRODUCT_PAGE("productInfo");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

