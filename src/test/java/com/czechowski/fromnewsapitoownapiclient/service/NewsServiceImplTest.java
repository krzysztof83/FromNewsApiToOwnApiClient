package com.czechowski.fromnewsapitoownapiclient.service;

import com.czechowski.fromnewsapitoownapiclient.model.News;
import com.czechowski.fromnewsapitoownapiclient.model.NewsArticle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@RunWith(SpringRunner.class)
@RestClientTest(NewsServiceImpl.class)
public class NewsServiceImplTest {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MockRestServiceServer server;

    private final String BASE_URL = "http://localhost:8081/news/";
    private final String PL = "pl";
    private final String SPORTS = "sports";
    private final String PAGE_PARAM = "page=";
    private final String PAGE_SIZE_PARAM = "pageSize=";
    private final String QUERY_PARAM = "q=";
    private final String AND = "&";

    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_PAGE_SIZE = 20;
    private final String EMPTY_QUERY_TO_SEARCH = "";


    private final String PL_SPORTS_URL = BASE_URL + PL + "/" + SPORTS + "?" + PAGE_PARAM + DEFAULT_PAGE + AND + PAGE_SIZE_PARAM + DEFAULT_PAGE_SIZE + AND + QUERY_PARAM + EMPTY_QUERY_TO_SEARCH;

    private News news;

    @Before
    public void setUp() throws Exception {

        news = new News();
        news.setCountry(PL);
        news.setCategory(SPORTS);

        NewsArticle newsArticle = new NewsArticle();
        news.getResponseArticles().add(newsArticle);

        newsArticle.setAuthor("Author");
        newsArticle.setTitle("Title");
        newsArticle.setDescription("description");
        newsArticle.setDate("2019-01-01");
        newsArticle.setSourceName("sourceName");
        newsArticle.setArticleUrl("https://sportowefakty.wp.pl/pilka-nozna/796944/gornik-zabrze-zainteresowany-arkadiuszem-malarzem");
        newsArticle.setImageUrl("https://sportowefakty.wp.pl/storage/featured_original/5c07d3c13694b3_42073717.jpg");

    }

    @Test
    public void findNews() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String newsInJsonString = objectMapper.writeValueAsString(news);

        this.server.expect(requestTo(PL_SPORTS_URL))
                .andRespond(withSuccess(newsInJsonString, MediaType.APPLICATION_JSON));

        newsService.findNews(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, EMPTY_QUERY_TO_SEARCH);

        server.verify();
    }

    @Test
    public void findNews_statusCode() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String newsInJsonString = objectMapper.writeValueAsString(news);

        this.server.expect(requestTo(PL_SPORTS_URL))
                .andRespond(withSuccess(newsInJsonString, MediaType.APPLICATION_JSON));

        ResponseEntity<News> actual = newsService.findNews(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, EMPTY_QUERY_TO_SEARCH);

        server.verify();
        assertEquals(HttpStatus.OK, actual.getStatusCode());

    }

    @Test
    public void findNews_contentTest() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String newsInJsonString = objectMapper.writeValueAsString(news);

        this.server.expect(requestTo(PL_SPORTS_URL))
                .andRespond(withSuccess(newsInJsonString, MediaType.APPLICATION_JSON));

        ResponseEntity<News> actual = newsService.findNews(PL, SPORTS, DEFAULT_PAGE, DEFAULT_PAGE_SIZE, EMPTY_QUERY_TO_SEARCH);


        server.verify();

        assertNotNull(actual);
        assertNotNull(actual.getBody());

        News actualNews = actual.getBody();

        assertNotNull(actualNews);

        assertEquals(PL, actualNews.getCountry());
        assertEquals(SPORTS, actualNews.getCategory());

        assertNotNull(actualNews.getResponseArticles());

        assertEquals(1,actualNews.getResponseArticles().size());

        assertNotNull(actualNews.getResponseArticles().get(0));

        NewsArticle actualNewsArticle = actualNews.getResponseArticles().get(0);

        assertEquals("Author",actualNewsArticle.getAuthor());

    }





}