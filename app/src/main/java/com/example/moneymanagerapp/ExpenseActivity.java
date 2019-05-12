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

import com.example.moneymanagerapp.Class.Expense;
import com.example.moneymanagerapp.Class.Income;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpenseActivity extends AppCompatActivity {

    private DatabaseReference database;

    private Button btSubmit, btCancel;
    private EditText etCategory, etValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        etCategory = (EditText) findViewById(R.id.etCategoryEx);
        etValues = (EditText) findViewById(R.id.etValuesEx);
        btSubmit = (Button) findViewById(R.id.proceedEx);
        btCancel = (Button) findViewById(R.id.cancelEx);

        database = FirebaseDatabase.getInstance().getReference();

        final Expense expense = (Expense) getIntent().getSerializableExtra("data");

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(etCategory.getText().toString()) && !isEmpty(etValues.getText().toString())) {
                    submitExpense(new Expense(etCategory.getText().toString(), etValues.getText().toString()));
                } else {
                    Snackbar.make(findViewById(R.id.proceed), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                }

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etCategory.getWindowToken(), 0);

                startActivity(new Intent(ExpenseActivity.this, HomeActivity.class));
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseActivity.this, HomeActivity.class));
            }
        });
    }

    private void submitExpense(Expense expense) {
        database.child("expense").push().setValue(expense).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etCategory.setText("");
                etValues.setText("");
                Toast.makeText(ExpenseActivity.this, "Expense successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isEmpty(String toString) {
        return TextUtils.isEmpty(toString);
    }
    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, ExpenseActivity.class);
    }
}
