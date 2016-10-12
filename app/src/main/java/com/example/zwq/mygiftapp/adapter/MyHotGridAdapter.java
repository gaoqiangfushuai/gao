package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.bean.HotInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/6.
 */
public class MyHotGridAdapter extends BaseAdapter {
    List<HotInfo.InfoBean.Push2Bean> list;
    Context context;

    public MyHotGridAdapter(List<HotInfo.InfoBean.Push2Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HotInfo.InfoBean.Push2Bean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.hot_push2_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.hot_push2_img);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_hot_push2_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());
        String logo = list.get(position).getLogo();
        Picasso.with(context).load(HttpUtils.BASE_URL+logo).into(viewHolder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_name;
    }
}
