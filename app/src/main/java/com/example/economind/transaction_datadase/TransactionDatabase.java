package com.example.economind.transaction_datadase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Transaction.class}, version = 1)
public abstract class TransactionDatabase extends RoomDatabase {
    private static TransactionDatabase instance;
    public abstract  TransactionDAO transactionDAO();
    public static synchronized TransactionDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TransactionDatabase.class, "transaction_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private  static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TransactionDAO transactionDAO;
        public PopulateDbAsyncTask(TransactionDatabase db) {
            transactionDAO = db.transactionDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            transactionDAO.insert_transaction(new Transaction("Продукты", -100, "01.01.2024"));

            return null;
        }
    }
}
