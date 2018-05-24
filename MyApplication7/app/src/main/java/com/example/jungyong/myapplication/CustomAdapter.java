package com.example.jungyong.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jungyong on 2017-10-05.
 */

public class CustomAdapter extends BaseAdapter {
    Context ctx;
    int layout;
    ArrayList<ListViewItem> list;
    LayoutInflater inf;

    public CustomAdapter(Context ctx, int layout, ArrayList<ListViewItem> list){
        this.ctx=ctx;
        this.layout= layout;
        this.list=list;
        inf=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return list.size();
    }
    public Object getItem(int position){
        return list.get(position);
    }
    public long getItemId(int position){
        return position;
    }
    public View getView(int position, View convertview, ViewGroup parent){

        if (convertview==null)
            convertview=inf.inflate(layout,null);

        TextView txtTitle = (TextView)convertview.findViewById(R.id.txtTitlte);
        TextView txtContent = (TextView)convertview.findViewById(R.id.txtContent);
        ImageView imgIcon = (ImageView)convertview.findViewById(R.id.imgIcon);

        ListViewItem dto = list.get(position);
        txtTitle.setText(dto.getTitle());
        txtContent.setText(dto.getContent());
        imgIcon.setImageResource(dto.getImgIcon());
        return convertview;
    }

}

