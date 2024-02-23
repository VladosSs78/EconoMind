package com.example.economind.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.economind.utils.MyAdapter;
import com.example.economind.R;
import com.example.economind.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageButton cashButton = (ImageButton) root.findViewById(R.id.cashButton);

        cashButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == motionEvent.ACTION_DOWN){
                    cashButton.setImageResource(R.drawable.button_cash_on);
                }
                else if (motionEvent.getAction() == motionEvent.ACTION_UP){
                    cashButton.setImageResource(R.drawable.button_cash_off);
                }
                return false;
            }
        });
        FragmentStateAdapter adapter = new MyAdapter(this);

        ViewPager2 viewPager = (ViewPager2) root.findViewById(R.id.PagerHome);
        viewPager.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}