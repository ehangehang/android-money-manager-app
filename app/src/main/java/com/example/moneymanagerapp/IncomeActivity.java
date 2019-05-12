package com.example.moneymanagerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moneymanagerapp.Class.Income;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IncomeActivity extends AppCompatActivity {

    private DatabaseReference database;

    private Button btSubmit, btCancel;
    private EditText etCategory, etValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        etCategory = (EditText) findViewById(R.id.etCategory);
        etValues = (EditText) findViewById(R.id.etValues);
        btSubmit = (Button) findViewById(R.id.proceed);
        btCancel = (Button) findViewById(R.id.cancel);

        database = FirebaseDatabase.getInstance().getReference();

        final Income income = (Income) getIntent().getSerializableExtra("data");

//        if (income != null) {
//            etCategory.setText(income.getCategory());
//            etValues.setText(income.getValues());
//            btSubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    income.setCategory(etCategory.getText().toString());
//                    income.setValues(etValues.getText().toString());
//                }
//            });
//        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isEmpty(etCategory.getText().toString()) && !isEmpty(etValues.getText().toString())) {
                        submitIncome(new Income(etCategory.getText().toString(), etValues.getText().toString()));
                    } else {
                        Snackbar.make(findViewById(R.id.proceed), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    }

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etCategory.getWindowToken(), 0);
                }
            });
//        }

//        btSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isEmpty(etCategory.getText().toString()) && !isEmpty(etValues.getText().toString())) {
//                    submitIncome(new Income(etCategory.getText().toString(), etValues.getText().toString()));
//                } else {
//                    Snackbar.make(findViewById(R.id.proceed), "Data cannot be empty", Snackbar.LENGTH_LONG).show();
//                }
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(
//                        etCategory.getWindowToken(), 0);
//            }
//        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IncomeActivity.this, HomeActivity.class));
            }
        });
    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void submitIncome(Income income) {
        database.child("income").push().setValue(income).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etCategory.setText("");
                etValues.setText("");
                Toast.makeText(IncomeActivity.this, "Income successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, IncomeActivity.class);
    }
}
