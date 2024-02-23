package com.example.economind.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.economind.ui.home.BalanceFragment;
import com.example.economind.ui.home.KeepFragment;
import com.example.economind.ui.home.HomeFragment;

public class MyAdapter extends FragmentStateAdapter {


    public MyAdapter(@NonNull HomeFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BalanceFragment();
            case 1:
                return new KeepFragment();
            default:
                return new BalanceFragment();
        }
    }
}
