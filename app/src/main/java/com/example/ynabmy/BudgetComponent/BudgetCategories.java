package com.example.ynabmy.BudgetComponent;

import java.util.ArrayList;
import java.util.List;

public class BudgetCategories {
    private List<BudgetItems> budgetItems = new ArrayList<>();
    private String categoryName;
    private double totalAssigned;
    private double totalAvailable;



    //getters and setters.
    public List<BudgetItems> getBudgetItems() {
        return budgetItems;
    }

    public void setBudgetItems(List<BudgetItems> budgetItems) {
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
