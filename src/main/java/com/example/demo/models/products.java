package com.example.demo.models;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categories;
    private String name;
    private double price;
    private double price_sale;
    private String description;
    private String unit;
    private int count;

    @Lob
    @Column(name = "image")
    private String image;

    public products() {

    }

    public products(Long id, String categories, String name, double price, double price_sale, String description, String unit, int count, String image) {
        this.id = id;
        this.categories = categories;
        this.name = name;
        this.price = price;
        this.price_sale = price_sale;
        this.description = description;
        this.unit = unit;
        this.count = count;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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

    public double getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(double price_sale) {
        this.price_sale = price_sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
