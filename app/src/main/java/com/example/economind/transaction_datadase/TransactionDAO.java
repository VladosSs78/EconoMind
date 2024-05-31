package com.example.economind.transaction_datadase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDAO {

    @Insert
    void insert_transaction (Transaction transaction);
    @Update
    void update_transaction(Transaction transaction);
    @Delete
    void delete_transaction(Transaction transaction);
    @Query("DELETE FROM transaction_table")
    void deleteAllTransactions();
    @Query("SELECT * FROM transaction_table")
    LiveData<List<Transaction>> getAllTransactions();

}
