package com.example.jungyong.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class VerticalViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;


    public VerticalViewHolder(View itemView) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.vertical_icon);


    }
}
