package com.example.economind.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economind.R;
import com.example.economind.ui.shop.itemListClass;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.ViewHolder> {
    private List<itemListClass> listItem = new ArrayList<>();
    private final Context context;

    public CustomArrayAdapter(List<itemListClass> exampleListArray, Context context) {
        this.listItem = exampleListArray;
        this.context = context;
    }


    //Сделать адаптер для RecyclerView
    @NonNull
    @Override
    public CustomArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.shop_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomArrayAdapter.ViewHolder holder, int position) {
        holder.setData(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public interface CustomAdapterOnItemClicked {
        void onCustomAdapterItemClicked(int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView cost;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameProduct);
            cost = (TextView) itemView.findViewById(R.id.costProduct);
            image = (ImageView) itemView.findViewById(R.id.imageItem);
        }
        public void setData(itemListClass itemListClass)
        {
            name.setText(itemListClass.getNameProduct());
            cost.setText(itemListClass.getCostProduct());
            Picasso.get().load(itemListClass.getImageURL()).fit().centerInside().into(image);
        }

    }
}
