package by.teachmeskills.eshop.controller;


import by.teachmeskills.eshop.Utils.AddItemToCart;
import by.teachmeskills.eshop.exceptions.ControllerException;
import by.teachmeskills.eshop.repository.domain.Category;
import by.teachmeskills.eshop.repository.domain.Product;
import by.teachmeskills.eshop.service.CategoryService;
import by.teachmeskills.eshop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.teachmeskills.eshop.PagesPathEnum.CATEGORY_LISTING_PAGE;
import static by.teachmeskills.eshop.RequestParamsEnum.CATEGORY_PARAM;
import static by.teachmeskills.eshop.Utils.SearchSize.searchSize;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public CategoryController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView getCategoryProducts(@RequestParam int categoryId,
                                            @RequestParam int pageNumber,
                                            @RequestParam int pageSize,
                                            @RequestParam String sortField,
                                            @RequestParam String sortDirection, HttpSession HttpSession) throws ControllerException {
        ModelMap model = new ModelMap();

        Optional<Category> category = categoryService.read(categoryId);

        searchSize(HttpSession, pageSize);

        Sort sortOrder = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber -1, pageSize, sortOrder);
        Page<Product> productList = productService.findProductsByCategoryId(categoryId, pageable);

        if(category.isPresent()){
            category.get().setProductList(productList.getContent());
            model.addAttribute(CATEGORY_PARAM.getValue(), category.get());
        }

        HttpSession.setAttribute("page", productList.getPageable().getPageNumber());
        return new ModelAndView(CATEGORY_LISTING_PAGE.getPath(), model);
    }

    @GetMapping("/addItem")
    public ModelAndView getCategoryProductsAndAddItem(@RequestParam int categoryId,
                                                      @RequestParam int itemId,
                                                      @RequestParam int pageNumber,
                                                      @RequestParam int pageSize,
                                                      @RequestParam String sortField,
                                                      @RequestParam String sortDirection,
                                                      HttpSession HttpSession) throws ControllerException {

        AddItemToCart.addItemTocart(HttpSession, itemId);
        return getCategoryProducts(categoryId, pageNumber, pageSize, sortField, sortDirection, HttpSession);
    }

}