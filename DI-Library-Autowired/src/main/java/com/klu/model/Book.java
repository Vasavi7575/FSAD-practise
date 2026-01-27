package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Book {

    private String isbn;
    private String title;
    private String author;
    private double price;

    public Book() {
        this.isbn = "ISBN-101";
        this.title = "Spring Framework Basics";
        this.author = "Rod Johnson";
        this.price = 550.0;
    }
    

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book [isbn=" + isbn + ", title=" + title +
               ", author=" + author + ", price=" + price + "]";
    }
}