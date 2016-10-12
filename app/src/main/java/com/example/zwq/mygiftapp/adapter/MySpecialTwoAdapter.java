package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.bean.SpecialTwoInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/8.
 */
public class MySpecialTwoAdapter extends BaseAdapter {
    List<SpecialTwoInfo.ListBean> list;
    Context context;

    public MySpecialTwoAdapter(List<SpecialTwoInfo.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SpecialTwoInfo.ListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.special_two_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img1 = (ImageView) convertView.findViewById(R.id.special_two_img);
            viewHolder.img2 = (ImageView) convertView.findViewById(R.id.special_two_img_author);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.special_two_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());;
        String path1 = list.get(position).getIconurl();
        String path2 = list.get(position).getAuthorimg();
        Picasso.with(context).load(HttpUtils.BASE_URL+path1).into(viewHolder.img1);
        Picasso.with(context).load(HttpUtils.BASE_URL+"/"+path2).into(viewHolder.img2);
        return convertView;
    }
    class ViewHolder{
        ImageView img1,img2;
        TextView tv_name;
    }
}
