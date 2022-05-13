package com.example.ynabmy.BudgetComponent;

public class BudgetItem {
    private String itemName = "NAME_NULL";
    private double assigned = -1;
    private double available = -1;


    //getters and setters.
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAssigned() {
        return assigned;
    }

    public void setAssigned(double assigned) {
        this.assigned = assigned;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }
}
