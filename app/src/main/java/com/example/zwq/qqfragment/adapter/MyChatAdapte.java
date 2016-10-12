package com.example.zwq.qqfragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zwq.qqfragment.R;
import com.example.zwq.qqfragment.bean.ChatInfo;
import com.example.zwq.qqfragment.fragment.ContactsFragment;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/9/7.
 */
public class MyChatAdapte extends BaseAdapter {
    List<ChatInfo> list ;
    Context context;

    public MyChatAdapte(List<ChatInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ChatInfo getItem(int position) {
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
            viewHolder = new ViewHolder();
            //为true是自己发的消息
            if (list.get(position).isFlag()){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_right,null,true);
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.content_right);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.name_right);
            }else{
                convertView = LayoutInflater.from(context).inflate(R.layout.item_left,null,true);
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.content_left);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.name_left);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置标记
        if (list.get(position).isFlag()){
            viewHolder.tv_name.setTag(list.get(position).getName());
            if (viewHolder.tv_name.getTag()!=null&&viewHolder.tv_name.getTag().equals(list.get(position).getName())){
                viewHolder.tv_name.setText("强哥");
                viewHolder.tv_content.setText(list.get(position).getContent());
            }
        }else{
            viewHolder.tv_name.setTag(list.get(position).getName());
            if (viewHolder.tv_name.getTag()!=null&&viewHolder.tv_name.getTag().equals(list.get(position).getName())){
                viewHolder.tv_name.setText(list.get(position).getName());
                viewHolder.tv_content.setText(list.get(position).getContent());
            }
        }


        return convertView;
    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_content;
    }
}
