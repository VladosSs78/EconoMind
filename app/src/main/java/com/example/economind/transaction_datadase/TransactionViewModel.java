package com.example.economind.transaction_datadase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository repository;
    private LiveData<List<Transaction>> allTransactions;

    public TransactionViewModel(@NonNull Application application) {
        super(application);
        repository = new TransactionRepository(application);
        allTransactions = repository.getAllTransactions();
    }

    public void insert_transacrion(Transaction transaction) {repository.insert_transaction(transaction);}
    public void update_transaction(Transaction transaction) { repository.update_transaction(transaction);}
    public void delete_transaction(Transaction transaction) { repository.delete_transaction(transaction);}
    public void deleteAllNotes() {repository.delete_all_transactions();}
    public  LiveData<List<Transaction>> getAllTransactions() {return  allTransactions; }
}
