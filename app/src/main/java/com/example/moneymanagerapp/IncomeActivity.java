package com.example.moneymanagerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IncomeActivity extends AppCompatActivity {

    private Button butt1, butt2, butt3;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        editText = findViewById(R.id.editText);
        editText.setEnabled(false);

        butt1 = findViewById(R.id.butt1);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(true);
                editText.setText(butt1.getText());
            }
        });
        butt2 = findViewById(R.id.butt2);
        butt3 = findViewById(R.id.butt3);
    }
}
