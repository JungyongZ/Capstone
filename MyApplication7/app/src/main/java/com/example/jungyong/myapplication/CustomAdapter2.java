package com.example.jungyong.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {
    Context ctx;
    int layout;
    ArrayList<ListViewItem2> list;
    LayoutInflater inf;

    public CustomAdapter2(Context ctx, int layout, ArrayList<ListViewItem2> list){
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

        ListViewItem2 dto = list.get(position);
        txtTitle.setText(dto.getTitle());
        txtContent.setText(dto.getContent());
        imgIcon.setImageBitmap(dto.getImgIcon());
        return convertview;
    }

}
