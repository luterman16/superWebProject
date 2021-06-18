package by.mmarshal.app.bootstrap;

public enum CommandsEnum {
    HOME_PAGE_COMMAND("start_page"),
    SIGN_IN_COMMAND("sign_in"),
    ITEM_COMMAND("item"),
    CART("cart"),
    PRODUCT_INFO("product_info"),
    DELETE_PRODUCT_IN_CARD("deleteincart"),
    REGISTRATION("registration"),
    CATEGORIES_COMMAND("categories");

    private final String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
