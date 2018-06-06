package com.example.jungyong.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalViewHolder> {
    private ArrayList<VerticalData> verticalDatas;

    public void setData(ArrayList<VerticalData> list){
        verticalDatas = list;
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vertical_recycler_items, parent, false);

        VerticalViewHolder holder = new VerticalViewHolder(view);

        return holder;


    }

    @Override
    public void onBindViewHolder(VerticalViewHolder holder, int position) {
        VerticalData data = verticalDatas.get(position);

holder.icon.setImageBitmap(data.getImg());
      //  holder.icon.setImageResource(data.getImg());

    }

    @Override
    public int getItemCount() {
        return verticalDatas.size();
    }
}
