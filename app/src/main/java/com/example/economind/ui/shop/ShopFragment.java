package com.example.economind.ui.shop;

import static com.example.economind.R.layout.item;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economind.R;
import com.example.economind.databinding.FragmentShopBinding;
import com.example.economind.utils.CustomArrayAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;

    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private List<itemListClass> itemListMain = new ArrayList<>();

    CustomArrayAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShopViewModel ShopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView listView = root.findViewById(R.id.recyclerView);
        GridLayoutManager LayoutManager = new GridLayoutManager(getContext(), 2);
        listView.setLayoutManager(LayoutManager);
        adapter = new CustomArrayAdapter(itemListMain, getActivity());
        listView.setAdapter(adapter);

        init();

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void init(){
        runnable = this::getWeb;
        secThread = new Thread(runnable);
        secThread.start();

    }
    private void getWeb(){
        try {
            doc = Jsoup.connect("https://5ka.ru/special_offers").get();

            Elements name = doc.getElementsByClass("item-name");
            Elements cost = doc.getElementsByClass("prices");
            Elements imageElement = doc.getElementsByClass("image-cont").select("img");

            Log.d("MyLog", "Name : " + name.get(0).text() );
            Log.d("MyLog","Cost : " + cost.get(0).text());
            Log.d("MyLog","URL : " + imageElement.get(0).attr("src"));

            for(int i = 0; i < 15; i++){
                itemListClass items = new itemListClass();

                items.setNameProduct(name.get(i).text());
                items.setCostProduct(cost.get(i).text().substring(0,6));
                String image = imageElement.get(i).attr("src");
                items.setImageURL(image);
                itemListMain.add(items);

            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}