package com.example.ynabmy.BudgetComponent;

import java.util.ArrayList;
import java.util.List;

public class BudgetCategory {
    private List<BudgetItem> budgetItems = new ArrayList<>();
    private String categoryName = "NAME_NULL";
    private double totalAssigned = -1;
    private double totalAvailable = -1;


    //getters and setters.
    public List<BudgetItem> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(List<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }

    public void addBudgetItem(BudgetItem budgetItem) {
        this.budgetItems.add(budgetItem);
    }

    public void removeBudgetItem(BudgetItem budgetItem){
        this.budgetItems.remove(budgetItem);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getTotalAssigned() {
        return totalAssigned;
    }

    public void setTotalAssigned(double totalAssigned) {
        this.totalAssigned = totalAssigned;
    }

    public double getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(double totalAvailable) {
        this.totalAvailable = totalAvailable;
    }
}
