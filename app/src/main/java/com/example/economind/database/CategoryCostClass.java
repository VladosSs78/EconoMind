package com.example.economind.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categorycostclass")
public class CategoryCostClass {
    @PrimaryKey(autoGenerate = true)
    public long costid;
    @ColumnInfo(name = "category_cost_name")
    public String categoryName;
    @ColumnInfo(name = "category_cost_sum")
    public double categorySum;
    @ColumnInfo(name = "date")
    public String date;

    public long getCostid() {
        return costid;
    }

    public void setCostid(long costid) {
        this.costid = costid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getCategorySum() {
        return categorySum;
    }

    public void setCategorySum(double categorySum) {
        this.categorySum = categorySum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
