package com.opet.hasgarddetouro_app.objects;

import java.io.Serializable;
import java.util.List;

public class Matress implements Serializable {
    private String name;
    private String description;
    private Float cashPrice;
    private List<String> images;

    public Matress() {
    }

    public Matress(String name, String description, Float cashPrice, List<String> images) {
        this.name = name;
        this.description = description;
        this.cashPrice = cashPrice;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(Float cashPrice) {
        this.cashPrice = cashPrice;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}