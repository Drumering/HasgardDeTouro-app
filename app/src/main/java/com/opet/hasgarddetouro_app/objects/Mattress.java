package com.opet.hasgarddetouro_app.objects;

public class Mattress {

    private String name;
    private String description;
    private Float cashPrice;
    private String conditions;

    public Mattress(String name, String description, Float cashPrice, String conditions) {
        this.name = name;
        this.description = description;
        this.cashPrice = cashPrice;
        this.conditions = conditions;
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

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
