package com.example.ynabmy.UserComponent;

import com.example.ynabmy.BudgetComponent.Budget;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Budget> budgets = new ArrayList();

    public User (final String username, final String password){
        this.username = username;
        this.password = password;
    }

    public boolean checkMatch (final String username, final String password){
        if(!this.username.equals(username)) return false;
        if(!this.password.equals(password)) return false;
        return true;
    }

    public String resetPassword (final String oldPassword, final String newPassword, final String confirmPassword){
        if(!this.password.equals(oldPassword)) return "old Password is incorrect.";
        if(newPassword != confirmPassword) return "new password is different from confirm password.";
        this.password = newPassword;
        return "Password changed successfully";
    }

    public String changeUsername (final String newUsername, final String password){
        if(this.password != password) return "Password is incorrect.";
        this.username = username;
        return "Username successfully changed";
    }

    public void addBudget (Budget budget){
        this.budgets.add(budget);
    }
    public void removeBudget (Budget budget){
        this.budgets.remove(budget);
    }
}
