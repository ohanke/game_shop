package com.game_shop.entity;

import java.util.List;

public class Product {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private List<Attribute> attributes;
    private Double priceNett;
    private Double priceGross;

    public Product(Long id, String name, Category category, String description, List<Attribute> attributes, Double priceNett, Double priceGross) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.attributes = attributes;
        this.priceNett = priceNett;
        this.priceGross = priceGross;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Double getPriceNett() {
        return priceNett;
    }

    public void setPriceNett(Double priceNett) {
        this.priceNett = priceNett;
    }

    public Double getPriceGross() {
        return priceGross;
    }

    public void setPriceGross(Double priceGross) {
        this.priceGross = priceGross;
    }
}
