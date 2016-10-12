package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.bean.SpecialOneContentInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/8.
 */
public class MySpecialOneContentAdapter extends BaseAdapter {
    List<SpecialOneContentInfo.ListBean> list;
    Context context;

    public MySpecialOneContentAdapter(List<SpecialOneContentInfo.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SpecialOneContentInfo.ListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.special_one_content_item,null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.special_one_game_img);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.special_one_game_name);
            viewHolder.btn = (Button) convertView.findViewById(R.id.special_one_game_btn);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getAppname());
        String path = list.get(position).getAppicon();
        Picasso.with(context).load(HttpUtils.BASE_URL+path).into(viewHolder.img);
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView tv_name;
        Button btn;
    }
}
