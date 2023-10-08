package com.kafeshka;

public class MenuItem {
    private String name;
    private double price;
    private double weightOrValue;
    private String description;
    private boolean vegetarian;
    private boolean spicy;
    private int calories;

    public MenuItem(String name, double price, double weightOrValue, String description, boolean vegetarian, boolean spicy, int calories) {
        this.name = name;
        this.price = price;
        this.weightOrValue = weightOrValue;
        this.description = description;
        this.vegetarian = vegetarian;
        this.spicy = spicy;
        this.calories = calories;
    }

    public MenuItem() {
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

    public double getWeightOrValue() {
        return weightOrValue;
    }

    public void setWeightOrValue(double weightOrValue) {
        this.weightOrValue = weightOrValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
