package com.example.moneymanagerapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
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
import com.example.moneymanagerapp.Class.Income;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageButton openNav;
    private TextView income, expense, aboutUs, category1, value1;

    private DrawerLayout drawer;
    private NavigationView navDrawer;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayaoutManager;

    private String category;
    private String value;

    private int values;

    private DatabaseReference database;

    ArrayList<Income> daftarIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        income = findViewById(R.id.incomeMenuDrawer);
        expense = findViewById(R.id.expenseMenuDrawer);
        aboutUs = findViewById(R.id.aboutUsMenuDrawer);
        openNav = findViewById(R.id.garisTiga);
        drawer = findViewById(R.id.drawer);
        navDrawer = findViewById(R.id.nvView);
        category1 = findViewById(R.id.kategoriTextV);
        value1 = findViewById(R.id.nominalTextV);

        navigationDrawer();

        mRecyclerView = findViewById(R.id.recyclerHistory);
        mRecyclerView.setHasFixedSize(true);

        mLayaoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayaoutManager);

        database = FirebaseDatabase.getInstance().getReference();
        database.child("income").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarIncome = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Income income = noteDataSnapshot.getValue(Income.class);
                    income.setKey(noteDataSnapshot.getKey());
                    daftarIncome.add(income);
                }
                mAdapter = new HistoryAdapter(daftarIncome, HomeActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, HomeActivity.class);
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
                startActivity(IncomeActivity.getActIntent(HomeActivity.this));
            }
        });

        expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ExpenseActivity.class));
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "About Us Menu", Toast.LENGTH_SHORT).show();
            }
        });
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
