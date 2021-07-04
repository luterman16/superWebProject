package by.mmarshal.app.bootstrap.command;

import by.mmarshal.app.bootstrap.PagesPathEnum;
import by.mmarshal.app.bootstrap.RequestParamsEnum;
import by.mmarshal.app.bootstrap.exceptions.CommandException;
import by.mmarshal.app.bootstrap.exceptions.RequestParamNullException;
import by.mmarshal.app.dao.domain.Order;
import by.mmarshal.app.dao.domain.Product;
import by.mmarshal.app.dao.domain.User;
import by.mmarshal.app.exceptions.ServiceException;
import by.mmarshal.app.service.OrderService;
import by.mmarshal.app.service.ProductService;
import by.mmarshal.app.service.UserService;
import by.mmarshal.app.service.impl.OrderServiceImpl;
import by.mmarshal.app.service.impl.ProductServiceImpl;
import by.mmarshal.app.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInCommandImpl implements BaseCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return checkReceivedUser(request);
    }

    private String checkReceivedUser(HttpServletRequest request) throws RequestParamNullException {
        String login;
        String password;
        Map<Order, List<Product>> ordersList = new HashMap<>();

        if(request.getParameter(RequestParamsEnum.LOGIN.getValue()) != null){
            login = request.getParameter(RequestParamsEnum.LOGIN.getValue());
            password =request.getParameter(RequestParamsEnum.PASSWORD.getValue());

            try {
                UserService userService = new UserServiceImpl();
                OrderService orderService = new OrderServiceImpl();
                ProductService productService = new ProductServiceImpl();
                //получаем юзера по логину
                User user = userService.getUserByEmail(login);
                // получаем список оредров юзера
                List<Order> orders = orderService.getOrderByUserId(user.getId());
                //проверяем логин на валидность
                if (login.equals(user.getEmail()) && password.equals(user.getPassword())) {

                    //формируем мапу оредов и покупок к этим ордерам
                    for (Order order: orders) {
                        ArrayList<Integer> ordersIdList = (ArrayList<Integer>) productService.getProductByOrderId(order.getId());
                        List<Product> listProductsByOrderId = new ArrayList<>();

                        for(Integer integer: ordersIdList) {
                            listProductsByOrderId.add(productService.read(integer));
                        }
                        ordersList.put(order, listProductsByOrderId);
                    }

                    request.setAttribute("userinfo", user);
                    request.setAttribute("orderslist", ordersList);

                    return PagesPathEnum.PERSONAL_INFO.getPath();
                } else {
                    return PagesPathEnum.SIGN_IN_PAGE.getPath();
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }

        } else {
            return PagesPathEnum.SIGN_IN_PAGE.getPath();
        }
        return PagesPathEnum.PERSONAL_INFO.getPath();
    }
}
