package com.example.economind;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PlusCash extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.economind.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.economind.EXTRA_TITLE";
    public static final String EXTRA_PRICE =
            "com.example.economind.EXTRA_PRICE";
    public static final String EXTRA_DATE =
            "com.example.economind.EXTRA_DATE";
    private Button addButton;
    private Button cancelButton;
    private EditText Title;
    private EditText Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_cash);

        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        Title = findViewById(R.id.editTitle);
        Price = findViewById(R.id.editPrice);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveTransaction();

                Toast.makeText(PlusCash.this, "Добавлено", Toast.LENGTH_SHORT).show();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlusCash.this, MainActivity.class));
            }
        });
    }

    private void saveTransaction() {
        String title = Title.getText().toString();
        String price = Price.getText().toString();


        if (title.trim().isEmpty() || price.trim().isEmpty()) {
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_PRICE, price);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

}