package com.example.moneymanagerapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneymanagerapp.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryAlbum> albumList;
//    public String category1, value1;
//
//    category1 = getIntent().getExtras().getString("category", "null");
//    value1 = getIntent().getExtras().getString("value", "0");

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView incomeExpense, category, nominal;
        public ImageView imgCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            incomeExpense = (TextView) itemView.findViewById(R.id.incomeExpenseTv);
            category = (TextView) itemView.findViewById(R.id.kategoriTextV);
            nominal = (TextView) itemView.findViewById(R.id.nominalTextV);
            imgCategory = (ImageView) itemView.findViewById(R.id.imageHistory);
        }
    }

    public HistoryAdapter(List<HistoryAlbum> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_history_cv, viewGroup, false);
        ViewHolder vH = new ViewHolder(itemView);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        HistoryAlbum album = albumList.get(i);
        viewHolder.incomeExpense.setText(album.getIncomeExpense());
        viewHolder.imgCategory.setImageResource(album.getImageHistory());
        viewHolder.category.setText(album.getCategory());
        viewHolder.nominal.setText(album.getNominal());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
