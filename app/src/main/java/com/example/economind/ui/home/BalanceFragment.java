package com.example.economind.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.economind.R;
import com.example.economind.SharedViewModel;
import com.example.economind.databinding.FragmentBalanceBinding;
import com.example.economind.ui.login.UserFragment;


public class BalanceFragment extends Fragment {
    private @NonNull FragmentBalanceBinding binding;
    private TextView textBalance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBalanceBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        textBalance = view.findViewById(R.id.textBalance);
        textBalance.setText("akj");
        return view;
    }

    public void updateItem(String s){
        if (textBalance != null){
            textBalance.setText(String.valueOf(s));
        }
    }

}