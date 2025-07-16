package spring.Poster.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {

    @JsonProperty("q")
    private String quote;
    @JsonProperty("a")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}