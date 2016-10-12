package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.bean.SpecialOneInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/8.
 */
public class MySpecialOnAdapter extends BaseAdapter {
    List<SpecialOneInfo.ListBean> list ;
    Context context;

    public MySpecialOnAdapter(List<SpecialOneInfo.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SpecialOneInfo.ListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.special_one_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.special_one_img);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.special_one_date);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.special_one_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_date.setText(list.get(position).getAddtime());
        String path = list.get(position).getIconurl();
        Picasso.with(context).load(HttpUtils.BASE_URL+path).into(viewHolder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_name,tv_date;
    }
}
