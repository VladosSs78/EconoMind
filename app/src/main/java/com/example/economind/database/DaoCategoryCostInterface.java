package com.example.economind.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface DaoCategoryCostInterface {
    @Query("SELECT * FROM CategoryCostClass ORDER BY costid DESC")
    List<CategoryCostClass> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CategoryCostClass categoryCost);

    @Query("UPDATE categorycostclass SET category_cost_name = :name WHERE costid = :id")
    void updateNameCost(int id, String name);

    @Delete
    void delete(CategoryCostClass categoryCost);
}
