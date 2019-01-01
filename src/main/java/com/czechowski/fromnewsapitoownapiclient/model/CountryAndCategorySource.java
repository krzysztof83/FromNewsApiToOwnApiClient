package com.czechowski.fromnewsapitoownapiclient.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class CountryAndCategorySource {

    Set<String> categorySet = new HashSet<>();
    Set<String> countrySet = new HashSet<>();

    public CountryAndCategorySource() {
        String categoryInString = "business entertainment general health science sports technology";
        String[] categoryTable = categoryInString.split(" ");

        categorySet.addAll(new HashSet<>(Arrays.asList(categoryTable)));

        String countryInString = "ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za";
        String[] countryTable = countryInString.split(" ");

        countrySet.addAll(new HashSet<>(Arrays.asList(countryTable)));
    }

    public Set<String> getCategorySet() {
        return categorySet;
    }

    public CountryAndCategorySource setCategorySet(Set<String> categorySet) {
        this.categorySet = categorySet;
        return this;
    }

    public Set<String> getCountrySet() {
        return countrySet;
    }

    public CountryAndCategorySource setCountrySet(Set<String> countrySet) {
        this.countrySet = countrySet;
        return this;
    }
}
