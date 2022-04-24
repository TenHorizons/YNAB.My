package com.example.ynabmy.BudgetComponent;

import java.util.ArrayList;
import java.util.List;

public class Budget {
    private List<BudgetCategories> budgetcategories = new ArrayList<>();
    private String budgetName;


    //getter and setters.
    public List<BudgetCategories> getBudgetcategories() {
        return budgetcategories;
    }

    public void setBudgetcategories(List<BudgetCategories> budgetcategories) {
        this.budgetcategories = budgetcategories;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }
}
