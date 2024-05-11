package com.example.mobilalkfejl;

import java.util.ArrayList;
import java.util.List;

public class BeverageDataSource {
    public static List<Beverage> getBeverages() {
        List<Beverage> beverageList = new ArrayList<>();

        beverageList.add(new Beverage("Vanilla Cola", "20cr", "Soft", "33 cl", "Refreshing as ever.", "cola"));
        beverageList.add(new Beverage("Craft Beer", "45cr", "Alcohol", "50 cl", "Pop a cold one.", "beer0"));
        beverageList.add(new Beverage("Clear Vodka", "120cr", "Alcohol", "70 cl", "Tough liquid.", "vodka"));
        beverageList.add(new Beverage("Sprite", "25cr", "Soft", "33 cl", "So good.", "sprite"));
        beverageList.add(new Beverage("Red Wine", "115cr", "Alcohol", "90 cl", "For special occasions!", "redwine"));
        beverageList.add(new Beverage("Fresh OJ", "60cr", "Juice", "100 cl", "Simply refreshing!", "orange"));
        beverageList.add(new Beverage("Bubble Tea", "35cr", "Tea", "44 cl", "Get yourself a treat!", "bubbletea"));
        beverageList.add(new Beverage("Coffee Latte", "35cr", "Coffee", "44 cl", "To start off your day!", "coffeelatte"));
        beverageList.add(new Beverage("Fresh AJ", "55cr", "Juice", "100 cl", "Refreshments for everyone!", "apple"));
        beverageList.add(new Beverage("Regular Coffee", "35cr", "Coffee", "44 cl", "To start off your day!", "coffee"));
        beverageList.add(new Beverage("Green Tea", "10cr", "Tea", "25 cl", "For a calm evening!", "greentea"));

        return beverageList;
    }
}
