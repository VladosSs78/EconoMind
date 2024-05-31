package com.example.economind.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.economind.PlusCash;
import com.example.economind.SharedViewModel;
import com.example.economind.transaction_datadase.Transaction;
import com.example.economind.transaction_datadase.TransactionViewModel;
import com.example.economind.ui.login.UserFragment;
import com.example.economind.ui.review.ReviewFragment;
import com.example.economind.ui.shop.ShopFragment;
import com.example.economind.utils.MyAdapter;
import com.example.economind.R;
import com.example.economind.databinding.FragmentHomeBinding;
import com.example.economind.utils.TransactionAdapter;

import java.util.List;

public class HomeFragment extends Fragment {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private FragmentHomeBinding binding;
    private SharedViewModel sharedViewModel;
    private TransactionViewModel transactionViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FragmentStateAdapter adapter = new MyAdapter(requireActivity());

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getChildFragmentManager();
            BalanceFragment balanceFragment = new BalanceFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.balance_container, balanceFragment, "balance")
                    .commit();
        }
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getSelectedItem().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                BalanceFragment balanceFragment = (BalanceFragment) getChildFragmentManager().findFragmentByTag("balance");
                if (balanceFragment != null){
                    balanceFragment.updateItem(s);
                }
            }
        });

        RecyclerView transactionList = root.findViewById(R.id.transaction_list);
        transactionList.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionList.setHasFixedSize(true);

        TransactionAdapter transactionAdapter = new TransactionAdapter();
        transactionList.setAdapter(transactionAdapter);

        transactionViewModel = new ViewModelProvider(requireActivity()).get(TransactionViewModel.class);
        transactionViewModel.getAllTransactions().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                transactionAdapter.setTransactions(transactions);
            }
        });

        ImageButton plusButton = (ImageButton) root.findViewById(R.id.cashButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlusCash.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(PlusCash.EXTRA_TITLE);
            double price = data.getIntExtra(PlusCash.EXTRA_PRICE, -1);
            String date = data.getStringExtra(PlusCash.EXTRA_DATE);

            Transaction transaction = new Transaction(title, price, date);
            transactionViewModel.insert_transacrion(transaction);

            Toast.makeText(this.getActivity(), "Transaction saved", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this.getActivity(), "Transaction not saved", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}