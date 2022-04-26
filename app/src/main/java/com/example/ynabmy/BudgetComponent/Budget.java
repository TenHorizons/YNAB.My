package com.example.ynabmy.BudgetComponent;

import java.util.ArrayList;
import java.util.List;

/**Class object for a single budget.
 * To have multiple budgets for user, have ArrayList<Budget> at
 * user class when it is created.*/
public class Budget {
    private List<BudgetCategory> budgetCategories = new ArrayList<>();
    private String budgetName = "Default Budget";

    //getter and setters.
    public List<BudgetCategory> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(List<BudgetCategory> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }

    public void addBudgetCategory(BudgetCategory budgetCategory){
        budgetCategories.add(budgetCategory);
    }

    public void removeBudgetCategory(BudgetCategory budgetCategory){
        budgetCategories.remove(budgetCategory);
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }
}
