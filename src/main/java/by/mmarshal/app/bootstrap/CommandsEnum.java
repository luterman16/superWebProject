package by.mmarshal.app.bootstrap;

public enum CommandsEnum {
    HOME_PAGE_COMMAND("start_page"),
    SIGN_IN_COMMAND("sign-in"),
    ITEM_COMMAND("item"),
    CART("cart"),
    CATEGORIES_COMMAND("categories");

    private final String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
