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
import com.example.zwq.mygiftapp.bean.SearchInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhuwenqiang on 2016/10/7.
 */
public class MySearchAdapter extends BaseAdapter {
    List<SearchInfo.ListBean> list;

    public MySearchAdapter(List<SearchInfo.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SearchInfo.ListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.search_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getGname());
        viewHolder.tv_giftName.setText(list.get(position).getGiftname());
        int num = list.get(position).getNumber();
        viewHolder.tv_num.setText("剩余:"+num);
        if (num==0){
            viewHolder.tv_btn.setText("…淘号…");
        }
        viewHolder.tv_date.setText("时间:"+list.get(position).getAddtime());
        String path = HttpUtils.BASE_URL+list.get(position).getIconurl();
        Picasso.with(context).load(path).into(viewHolder.imageView);
        return convertView;
    }
    class ViewHolder {
        @BindView(R.id.tv_search_name)
        TextView tv_name;
        @BindView(R.id.tv_search_giftName)
        TextView tv_giftName;
        @BindView(R.id.tv_search_num)
        TextView tv_num;
        @BindView(R.id.tv_search_date)
        TextView tv_date;
        @BindView(R.id.tv_search_btn)
        Button tv_btn;
        @BindView(R.id.img_search_list)
        ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }

    }

}
