package com.example.moneymanagerapp.Class;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Income implements Serializable {
    private String key;
    private String category;
    private String values;

    public Income(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return " "+ category + "\n" +
                " " + values;
    }

    public Income(String cat, String val ){
        category = cat;
        values = val;
    }
}
