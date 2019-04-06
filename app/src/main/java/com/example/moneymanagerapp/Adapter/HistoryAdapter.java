package com.example.moneymanagerapp.Adapter;

import android.widget.ImageView;

public class HistoryAdapter {
    private String incomeExpense;
    private String category;
    private String nominal;
    private int imageHistory;

    public HistoryAdapter(){
    }

    public HistoryAdapter(int imageHistory, String incomeExpense, String category, String nominal){
        this.imageHistory = imageHistory;
        this.incomeExpense = incomeExpense;
        this.category = category;
        this.nominal = nominal;
    }

    public int getImageHistory() {
        return imageHistory;
    }

    public void setImageHistory(int imageHistory) {
        this.imageHistory = imageHistory;
    }

    public String getIncomeExpense() {
        return incomeExpense;
    }

    public void setIncomeExpense(String incomeExpense) {
        this.incomeExpense = incomeExpense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
}
