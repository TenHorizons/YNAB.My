package com.example.ynabmy.BudgetComponent;

import java.util.ArrayList;
import java.util.List;

public class BudgetCategory {
    private List<BudgetItem> budgetItems = new ArrayList<>();
    private String categoryName = "NAME_NULL";
    private double totalAssigned = -1;
    private double totalAvailable = -1;

    public BudgetCategory(final String name,final int totalAssigned,final int totalAvailable){
        this.categoryName = name;
        this.totalAssigned = totalAssigned;
        this.totalAvailable = totalAvailable;
    }

    //getters and setters.
    public List<BudgetItem> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(List<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
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
