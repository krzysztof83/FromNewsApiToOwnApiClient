package com.czechowski.fromnewsapitoownapiclient.model;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class CountryAndCategorySource {

    SortedSet<String> categorySet = new TreeSet<>();
    SortedSet<String> countrySet = new TreeSet<>();

    public CountryAndCategorySource() {
        String categoryInString = "business entertainment general health science sports technology";
        String[] categoryTable = categoryInString.split(" ");

        categorySet.addAll(Arrays.asList(categoryTable));


        String countryInString = "ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za";
        String[] countryTable = countryInString.split(" ");

        countrySet.addAll(Arrays.asList(countryTable));
    }

    public SortedSet<String> getCategorySet() {
        return categorySet;
    }

    public SortedSet<String> getCountrySet() {
        return countrySet;
    }

}
