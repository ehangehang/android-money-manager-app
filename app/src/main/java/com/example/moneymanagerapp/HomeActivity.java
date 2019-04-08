package com.example.moneymanagerapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagerapp.Adapter.HistoryAdapter;
import com.example.moneymanagerapp.Adapter.HistoryAlbum;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageButton openNav;
    private TextView income, expense, aboutUs, category1, value1, incomeExpense;

    private DrawerLayout drawer;
    private NavigationView navDrawer;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayaoutManager;

    private String category;
    private String value;

    private int values;

    ArrayList<HistoryAlbum> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

//        values = Integer.parseInt(getIntent().getExtras().getString("value"));

        initializeViews();

        navigationDrawer();
        createAlbumList();

        buildRecyclerView();

    }
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerHistory);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HistoryAdapter(albumList);
        mLayaoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayaoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void createAlbumList() {
        if (category != null){
            category = getIntent().getExtras().getString("category", "null");
        }
        if (value != null){
            value = getIntent().getExtras().getString("value", "0");
        }

        albumList = new ArrayList<>();
        albumList.add(new HistoryAlbum(R.drawable.award, "Income", "Award", "200000"));
//        albumList.add(new HistoryAlbum(R.drawable.coupon, "Income", category, value));

    }

    private void initializeViews() {
        income = findViewById(R.id.incomeMenuDrawer);
        expense = findViewById(R.id.expenseMenuDrawer);
        aboutUs = findViewById(R.id.aboutUsMenuDrawer);
        openNav = findViewById(R.id.garisTiga);
        drawer = findViewById(R.id.drawer);
        navDrawer = findViewById(R.id.nvView);
        category1 = findViewById(R.id.kategoriTextV);
        value1 = findViewById(R.id.nominalTextV);
        incomeExpense = findViewById(R.id.incomeExpenseTv);
    }

    private void navigationDrawer() {
        openNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });
        setDrawerContent(navDrawer);

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, IncomeActivity.class));
                incomeDialog();
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Expense Menu", Toast.LENGTH_SHORT).show();
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "About Us Menu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void incomeDialog() {
        final Dialog dialog1 = new Dialog(HomeActivity.this, android.R.style.Theme_Black_NoTitleBar);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000ffff")));
        dialog1.setContentView(R.layout.item_income);

        final EditText edtextCategory = (EditText) dialog1.findViewById(R.id.editTextCategory);
        final EditText edtextValue = (EditText) dialog1.findViewById(R.id.editTextValue);

        Button cancel1 = (Button) dialog1.findViewById(R.id.cancel);
        Button proceed1 = (Button) dialog1.findViewById(R.id.proceed);

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.cancel();
            }
        });
        proceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("category", String.valueOf(edtextCategory.getText()));
                intent.putExtra("value", String.valueOf(edtextValue.getText()));
                startActivity(intent);
//                insertIncome(position);
                dialog1.cancel();
                finish();
            }
        });
        dialog1.setCancelable(false);
        dialog1.show();
    }

    private void insertIncome(int position) {
        albumList.add(0, new HistoryAlbum(R.drawable.award, "Income", category, value));
        mAdapter.notifyItemInserted(position);
    }

    private void setDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.incomeDrawer:
            break;
            case R.id.expenseDrawer:
                break;
            case R.id.aboutUs:
                break;
            default:
        }

        drawer.closeDrawers();
    }
}
