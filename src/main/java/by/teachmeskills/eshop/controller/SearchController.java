package by.teachmeskills.eshop.controller;

import by.teachmeskills.eshop.Utils.AddItemToCart;
import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.service.ProductService;
import by.teachmeskills.eshop.service.SearchProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static by.teachmeskills.eshop.PagesPathEnum.FILTER_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.ITEMS;
import static by.teachmeskills.eshop.Utils.CheckCort.checkCort;
import static by.teachmeskills.eshop.Utils.SearchSize.searchSize;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final ProductService productService;
    private final SearchProductService searchProductService;

    public SearchController(ProductService productService, SearchProductService searchProductService) {
        this.productService = productService;
        this.searchProductService = searchProductService;
    }

    @GetMapping
    public ModelAndView getSearchPage(@RequestParam int pageNumber,
                                      @RequestParam int pageSize,
                                      @RequestParam String sortField,
                                      @RequestParam String sortDirection,
                                      HttpSession HttpSession) throws ControllerException {

        searchSize(HttpSession, pageSize);
        String searchText = HttpSession.getAttribute("searchText").toString();

        Sort sortOrder = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortOrder);

        Page<Product> productList = productService.findProductsByNameOrDescriptionLike(searchText, searchText, pageable);
        ModelMap model = new ModelMap();

        model.addAttribute(ITEMS.getValue(), productList.getContent());

        HttpSession.setAttribute("page", productList.getPageable().getPageNumber());
        HttpSession.setAttribute("totalElements", productList.getTotalElements());
        HttpSession.setAttribute("productListBySearch", productList.getContent());
        HttpSession.setAttribute("searchText", searchText);
        return new ModelAndView(FILTER_PAGE.getPath(), model);

    }

    @GetMapping("/addItem")
    public ModelAndView getSearchPageAndAddItem(@RequestParam int itemId,
                                                @RequestParam int pageNumber,
                                                @RequestParam int pageSize,
                                                @RequestParam String sortField,
                                                @RequestParam String sortDirection,
                                                HttpSession HttpSession) throws ControllerException {


        AddItemToCart.addItemTocart(HttpSession, itemId);
        return getSearchPage(pageNumber, pageSize, sortField, sortDirection, HttpSession);
    }

    @PostMapping
    public ModelAndView getSearchPage(@RequestParam String searchText, HttpSession HttpSession) throws ControllerException {
        checkCort(HttpSession);
        searchSize(HttpSession);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("name").ascending());

        Page<Product> productList = productService.findProductsByNameOrDescriptionLike(searchText, searchText, pageable);
        ModelMap model = new ModelMap();

        model.addAttribute(ITEMS.getValue(), productList.getContent());
        HttpSession.setAttribute("nextPage", productList.getPageable().getPageNumber() + 2);
        HttpSession.setAttribute("totalElements", productList.getTotalElements());
        HttpSession.setAttribute("productListBySearch", productList.getContent());
        HttpSession.setAttribute("searchText", searchText);
        return new ModelAndView(FILTER_PAGE.getPath(), model);
    }
}


