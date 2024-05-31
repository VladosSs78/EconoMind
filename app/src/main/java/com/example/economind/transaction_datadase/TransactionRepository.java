package com.example.economind.transaction_datadase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionRepository {
    private TransactionDAO transactionDAO;
    private LiveData<List<Transaction>> allTransactions;

    public TransactionRepository(Application application){
        TransactionDatabase database = TransactionDatabase.getInstance(application);
        transactionDAO = database.transactionDAO();
        allTransactions = transactionDAO.getAllTransactions();
    }
    public void insert_transaction(Transaction transaction) { new InsertTransactionAsyncTask(transactionDAO).execute(transaction);}
    public void update_transaction(Transaction transaction) { new UpdateTransactionAsyncTask(transactionDAO).execute(transaction);}
    public void delete_transaction(Transaction transaction) { new DeleteTransactionAsyncTask(transactionDAO).execute(transaction);}
    public void delete_all_transactions() { new DeleteAllTransactionsAsyncTask(transactionDAO).execute();}
    public LiveData<List<Transaction>> getAllTransactions() { return allTransactions;}

    private class InsertTransactionAsyncTask  extends AsyncTask<Transaction, Void, Void>{
        private TransactionDAO transactionDAO;
        public InsertTransactionAsyncTask(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(Transaction... transactions){
            transactionDAO.insert_transaction(transactions[0]);
            return null;
        }
    }
    private class UpdateTransactionAsyncTask  extends AsyncTask<Transaction, Void, Void>{
        private TransactionDAO transactionDAO;
        public UpdateTransactionAsyncTask(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(Transaction... transactions){
            transactionDAO.update_transaction(transactions[0]);
            return null;
        }
    }
    private class DeleteTransactionAsyncTask  extends AsyncTask<Transaction, Void, Void>{
        private TransactionDAO transactionDAO;
        public DeleteTransactionAsyncTask(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(Transaction... transactions){
            transactionDAO.delete_transaction(transactions[0]);
            return null;
        }
    }

    private class DeleteAllTransactionsAsyncTask  extends AsyncTask<Void, Void, Void>{
        private TransactionDAO transactionDAO;
        public DeleteAllTransactionsAsyncTask(TransactionDAO transactionDAO) {
            this.transactionDAO = transactionDAO;
        }

        @Override
        protected Void doInBackground(Void... voids){
            transactionDAO.deleteAllTransactions();
            return null;
        }
    }
}
