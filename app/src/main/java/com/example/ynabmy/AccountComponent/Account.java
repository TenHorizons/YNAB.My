package com.example.ynabmy.AccountComponent;

public class Account
{
    private Integer id;
    private String budget_type;
    private String nickname;
    private Float balance;
    private Float interest_rate;
    private Float monthly_payment;

    public void setAccount(Integer id_arg, String budget_type_arg, String nickname_arg, Float balance_arg, Float interest_rate_arg, Float monthly_payment_arg) {
        id = id_arg;
        budget_type=budget_type_arg;
        nickname = nickname_arg;
        balance = balance_arg;
        interest_rate = interest_rate_arg;
        monthly_payment = monthly_payment_arg;
    }

    public Integer getId() {
        return id;
    }
    public String getBudgetType() { return budget_type; }
    public String getNickname() {
        return nickname;
    }
    public Float getBalance() {
        return balance;
    }
    public Float getInterestRate() {
        return interest_rate;
    }
    public Float getMonthlyPayment() {
        return monthly_payment;
    }
}
