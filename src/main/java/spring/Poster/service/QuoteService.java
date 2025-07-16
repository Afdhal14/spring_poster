package spring.Poster.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.Poster.model.Quote;

@Service
public class QuoteService {

    public Quote fetchQuote()
    {
        String url ="https://zenquotes.io/api/random";
        RestTemplate restTemplate = new RestTemplate();
        Quote quotes[] = restTemplate.getForObject(url, Quote[].class);

        if(quotes!=null && quotes.length>0)
        {
            return quotes[0];
        }
        else {
            Quote fallback = new Quote();
            fallback.setQuote("Keep going. Everything you need will come to you.");
            fallback.setAuthor("Unknown");
            return fallback;
        }
    }
}
