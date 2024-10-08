package com.example.economind.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economind.R;
import com.example.economind.transaction_datadase.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {
    private List<Transaction> transactions = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);

        return new TransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction currentTransaction = transactions.get(position);
        holder.textViewTitle.setText(currentTransaction.getTitle());
        holder.textViewPrice.setText(String.valueOf(currentTransaction.getPrice()));
        holder.textViewDate.setText(currentTransaction.getDate());
    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }
    public void setTransactions(List<Transaction> notes){
        this.transactions = notes;
        notifyDataSetChanged();
    }

    public Transaction getTransactionAt(int position){
        return transactions.get(position);
    }

    class TransactionHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewPrice;
        private TextView textViewDate;

        public TransactionHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            textViewDate = itemView.findViewById(R.id.text_view_date);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(transactions.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Transaction transaction);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
