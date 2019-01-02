package com.czechowski.fromnewsapitoownapiclient.controller;

import com.czechowski.fromnewsapitoownapiclient.model.CountryAndCategorySource;
import com.czechowski.fromnewsapitoownapiclient.model.News;
import com.czechowski.fromnewsapitoownapiclient.model.SearchValue;
import com.czechowski.fromnewsapitoownapiclient.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
@Controller
public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/index/")
    public String showIndex(Model model) {

        model.addAttribute("countryAndCategorySource", new CountryAndCategorySource());
        model.addAttribute("searchValue",new SearchValue());

        return "index";
    }

    @PostMapping("/news/")
    public String showNewsList( @ModelAttribute("searchValue") SearchValue searchValue, BindingResult bindingResult, Model model) {


        if(bindingResult.hasErrors()){

            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.toString());
            });
            model.addAttribute("countryAndCategorySource",new CountryAndCategorySource());
            return "index";
        }

        ResponseEntity<News> responseEntity = newsService.findNews(searchValue.getCountry(),searchValue.getCategory(),searchValue.getPage(),searchValue.getPageSize(), searchValue.getQueryToSearch());

        model.addAttribute("news",responseEntity.getBody());

        return "/news/newslist";
    }

}
