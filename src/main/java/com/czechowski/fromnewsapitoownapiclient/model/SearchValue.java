package com.czechowski.fromnewsapitoownapiclient.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class SearchValue {

    private String country;
    private String category;
    private String queryToSearch;

    @Min(0)
    private Integer page = 0;

    @Min(0)
    @Max(100)
    private Integer pageSize = 20;

    public String getCountry() {
        return country;
    }

    public SearchValue setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SearchValue setCategory(String category) {
        this.category = category;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public SearchValue setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public SearchValue setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getQueryToSearch() {
        return queryToSearch;
    }

    public void setQueryToSearch(String queryToSearch) {
        this.queryToSearch = queryToSearch;
    }
}
