package com.example.ynabmy.BudgetComponent;

import java.util.ArrayList;
import java.util.List;

public class BudgetList {
    //Using ArrayList instead of LinkedList because get/set happens more than add/remove.
    //Not sure about using Vector, assuming this isn't multi-threaded.
    private List<String> budgetItems = new ArrayList<String>();

    public BudgetList(){}
    public BudgetList(List<String> budgetItems){
        this.budgetItems = budgetItems;
    }
    public List<String> getBudgetItems(){
        return budgetItems;
    }
    public String getBudgetItem(int position){
        return budgetItems.get(position);
    }
    public int getItemCount(){
        return budgetItems.size();
    }
    public void addBudgetItem(String budgetItem){
        budgetItems.add(budgetItem);
    }
    public void removeBudgetItem(String budgetItem){
        budgetItems.remove(budgetItem);
    }
    public void addBudgetItem(int budgetItemPosition, String budgetItem){
        budgetItems.add(budgetItemPosition, budgetItem);
    }
    public void removeBudgetItem(int budgetItemPosition){
        budgetItems.remove(budgetItemPosition);
    }
}
