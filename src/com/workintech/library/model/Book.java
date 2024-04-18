package com.workintech.library.model;

import com.workintech.library.enums.Category;
import com.workintech.library.enums.Genre;
import com.workintech.library.enums.Status;

public class Book {
    private Long ID;
    private  String author;
    private String name;
    private double price;
    private int stock;
    private Genre genre; //tür
    private Status status; //durum
    private Category category;

    public Book(Long ID, String author, String name, double price, int stock, Genre genre, Status status, Category category) {
        this.ID = ID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.genre = genre;
        this.status = status;
        this.category = category;
    }

    public Long getID() {
        return ID;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", genre=" + genre +
                ", status=" + status +
                ", category=" + category +
                '}';
    }
}
