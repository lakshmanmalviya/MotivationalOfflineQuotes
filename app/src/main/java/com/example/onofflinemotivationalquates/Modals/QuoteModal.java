package com.example.onofflinemotivationalquates.Modals;

public class QuoteModal {
    String id;
    String name,quote;

    public QuoteModal() {
    }

    public QuoteModal(String id, String name, String quote) {
        this.id = id;
        this.name = name;
        this.quote = quote;
    }

    public QuoteModal(String name, String quote) {
        this.name = name;
        this.quote = quote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
