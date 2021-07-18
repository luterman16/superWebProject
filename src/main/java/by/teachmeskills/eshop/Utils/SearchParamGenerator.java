package by.teachmeskills.eshop.Utils;

import java.util.HashMap;
import java.util.Map;

public class SearchParamGenerator {
    public static Map<String, String> generatorSearchParams(String searchText, String category, String priceBefore, String priceAfter){
        Map<String, String> params = new HashMap<>();
        params.put("productName", searchText);
        params.put("descriptionName", searchText);
        params.put("category", category);
        params.put("priceBefore", priceBefore);
        params.put("priceAfter", priceAfter);
        return params;
    }
}
