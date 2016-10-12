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
public class MyHotListAdapter extends BaseAdapter {
    List<HotInfo.InfoBean.Push1Bean> list;
    Context context;

    public MyHotListAdapter(List<HotInfo.InfoBean.Push1Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public HotInfo.InfoBean.Push1Bean getItem(int position) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.hot_push1_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.hot_push1_img);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_hot_name);
            viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_people_num);
            viewHolder.tv_size = (TextView) convertView.findViewById(R.id.tv_hot_size);
            viewHolder.tv_type = (TextView) convertView.findViewById(R.id.tv_hot_type);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_type.setText("类型:"+list.get(position).getTypename());
        viewHolder.tv_size.setText("大小:"+list.get(position).getSize());
        viewHolder.tv_num.setText(list.get(position).getClicks()+"人在玩");
        String logo = list.get(position).getLogo();
        Picasso.with(context).load(HttpUtils.BASE_URL+logo).into(viewHolder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_name,tv_type,tv_size,tv_num;
    }
}
