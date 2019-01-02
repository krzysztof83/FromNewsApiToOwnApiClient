package com.czechowski.fromnewsapitoownapiclient.controller;

import com.czechowski.fromnewsapitoownapiclient.model.News;
import com.czechowski.fromnewsapitoownapiclient.service.NewsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
@AutoConfigureMockMvc
public class NewsControllerTest {

    @MockBean
    NewsServiceImpl newsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showIndex_statusTest() throws Exception {

        mockMvc.perform(get("http://localhost:8081/index/"))
                .andExpect(status().isOk());
    }

    @Test
    public void showIndex_viewNameTest() throws Exception {

        mockMvc.perform(get("http://localhost:8081/index/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void showIndex_modelAtributes() throws Exception {

        mockMvc.perform(get("http://localhost:8081/index/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("countryAndCategorySource"))
                .andExpect(model().attributeExists("searchValue"));
    }

    @Test
    public void showNewsList_statusTest() throws Exception {

        News news = new News();
        news.setCountry("pl");
        news.setCategory("sports");

        final ResponseEntity<News> responseEntity = ResponseEntity.ok(news);

        when(newsService.findNews(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(responseEntity);

        mockMvc.perform(post("/news/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("country", "pl")
                .param("category", "sports")
                .param("page", "0")
                .param("pageSize", "20")
                .param("q", ""))
                .andExpect(status().isOk());
    }

    @Test
    public void showNewsList_viewNameTest() throws Exception {
        News news = new News();
        news.setCountry("pl");
        news.setCategory("sports");

        final ResponseEntity<News> responseEntity = ResponseEntity.ok(news);
        when(newsService.findNews(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(responseEntity);

        mockMvc.perform(post("/news/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("country", "pl")
                .param("category", "sports")
                .param("page", "0")
                .param("pageSize", "20")
                .param("q", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("/news/newslist"));
    }

    @Test
    public void showNewsList_modelAtributes() throws Exception {
        News news = new News();
        news.setCountry("pl");
        news.setCategory("sports");

        final ResponseEntity<News> responseEntity = ResponseEntity.ok(news);
        when(newsService.findNews(anyString(), anyString(), anyInt(), anyInt(), anyString())).thenReturn(responseEntity);

        mockMvc.perform(post("/news/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("country", "pl")
                .param("category", "sports")
                .param("page", "0")
                .param("pageSize", "20")
                .param("q", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("news"));
    }

}