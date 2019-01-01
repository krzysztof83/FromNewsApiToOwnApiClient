package com.czechowski.fromnewsapitoownapiclient.service;

import com.czechowski.fromnewsapitoownapiclient.model.News;
import org.springframework.http.ResponseEntity;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public interface NewsService {

    ResponseEntity<News> findNews(String country, String category, int page, int pageSize, String queryToSearch);

    String getBaseUrl();
}
