package com.example.mobilalkfejl;

public class Beverage {
    private String name;
    private String price;
    private String category;
    private String amount;
    private String desc;
    private String img;

    public Beverage(String name, String price, String category, String amount, String desc, String img) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
        this.desc = desc;
        this.img = img;
    }

    public Beverage() {
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getImg() {
        return img;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

