package com.example.economind.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.economind.SharedViewModel;
import com.example.economind.databinding.FragmentUserBinding;
import com.example.economind.ui.home.BalanceFragment;

public class UserFragment extends Fragment {
    private FragmentUserBinding binding;
    private EditText newBalance;
    private Button Share;
    public String BalancePub;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        Share = binding.buttonEnterBalance;
        newBalance = binding.editNewBalance;
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BalancePub = newBalance.getText().toString();
                // Отправляем данные
                sharedViewModel.selectItem(BalancePub);
                Toast.makeText(getActivity(), "Сохранено " + BalancePub, Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}