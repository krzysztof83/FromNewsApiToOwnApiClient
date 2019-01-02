package com.czechowski.fromnewsapitoownapiclient.service;

import com.czechowski.fromnewsapitoownapiclient.model.News;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final String HOST = "http://localhost:";
    private static final String PORT = "8081";
    private static final String BASEPATH = "/news/";

    private RestTemplate restTemplate;

    public NewsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public ResponseEntity<News> findNews(String country, String category, int page, int pageSize, String queryToSearch) {

        String url = getUriWithPagination(country, category, page, pageSize, queryToSearch);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<News> responseObj = restTemplate.exchange(
                url, HttpMethod.GET, entity, News.class);

        return responseObj;
    }

    @Override
    public String getBaseUrl() {
        return HOST + PORT + BASEPATH;
    }

    private String getUriWithPagination(String country, String category, int page, int pageSize, String queryToSearch) {

        String uri = getBaseUrl() + country + "/" + category;

        return UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("q", queryToSearch)
                .toUriString();
    }
}
