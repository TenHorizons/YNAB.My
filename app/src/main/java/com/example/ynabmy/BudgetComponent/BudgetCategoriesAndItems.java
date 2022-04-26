package com.example.ynabmy.BudgetComponent;

import com.example.ynabmy.R;

import java.util.HashMap;
import java.util.Map;

public class BudgetCategoriesAndItems {
    private Map<String,Integer> budgetCategoriesAndItems = new HashMap<>();

    public BudgetCategoriesAndItems() {
        budgetCategoriesAndItems.put("Immediate Obligations",R.array.Immediate_Obligations_Default_Items);
        budgetCategoriesAndItems.put("True Expenses",R.array.True_Expenses_Default_Items);
        budgetCategoriesAndItems.put("Debt Payments",R.array.Debt_Payment_Default_Items);
        budgetCategoriesAndItems.put("Quality of Life Goals",R.array.Quality_of_Life_Goals_Default_Items);
        budgetCategoriesAndItems.put("Just for Fun",R.array.Just_for_Fun_Default_Items);
    }
    public int getStringArrayNameUsingCategoryName(String categoryName){
        return budgetCategoriesAndItems.get(categoryName);
    }
}
