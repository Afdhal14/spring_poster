package spring.Poster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.Poster.model.Quote;
import spring.Poster.service.QuoteService;

@RestController
@CrossOrigin(origins = "*")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/quote")
    public Quote getQuote()
    {
        return quoteService.fetchQuote();
    }
}