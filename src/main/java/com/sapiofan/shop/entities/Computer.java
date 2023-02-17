package com.sapiofan.shop.entities;

public class Computer {
    private Long id;

    private String name;

    private String brand;

    private Integer price;

    public Computer() {
    }

    public Computer(Long id, String name, String brand, Integer price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nComputer: " +
                "\nID = " + id +
                "\nName = " + name +
                "\nBrand = " + brand +
                "\nPrice = " + price;
    }
}

