package com.example.ynabmy.BudgetComponent;

public class BudgetItems {
    private String itemName;
    private double assigned;
    private double available;

    //constructor for when BudgetItems is initialized
    public BudgetItems(){
        this.itemName = "NAME_NULL";
        this.assigned = 0;
        this.available = 0;
    }

    public BudgetItems(final String itemName){
        this.itemName = itemName;
        this.assigned = 0;
        this.available = 0;
    }


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
