package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.CommandsEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, BaseCommand> COMMAND_LIST = new HashMap<>();

    static {
        COMMAND_LIST.put(CommandsEnum.HOME_PAGE_COMMAND.getCommand(), new HomePageCommandImpl());
        COMMAND_LIST.put(CommandsEnum.SIGN_IN_COMMAND.getCommand(), new SignInCommandImpl());
        COMMAND_LIST.put(CommandsEnum.ITEM_COMMAND.getCommand(), new ItemPage());
        COMMAND_LIST.put(CommandsEnum.CATEGORIES_COMMAND.getCommand(), new CategoriesPage());
        COMMAND_LIST.put(CommandsEnum.CART.getCommand(), new CartPage());
    }

    public static BaseCommand defineCommand(HttpServletRequest request) {
        String commandKey = request.getParameter(RequestParamsEnum.COMMAND.getValue());
        if(commandKey == null || commandKey.isEmpty()){
            commandKey = CommandsEnum.SIGN_IN_COMMAND.getCommand();
        }



        return COMMAND_LIST.get(commandKey);
    }
}
