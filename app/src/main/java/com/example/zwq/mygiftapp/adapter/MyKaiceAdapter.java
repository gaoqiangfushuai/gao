package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.bean.KaiCeInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhuwenqiang on 2016/10/4.
 */
public class MyKaiceAdapter extends BaseAdapter {
    List<KaiCeInfo.InfoBean> list;
    Context context;

    public MyKaiceAdapter(List<KaiCeInfo.InfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public KaiCeInfo.InfoBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.kaice_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getGname());
        viewHolder.tv_compant.setText("运营商:"+list.get(position).getOperators());
        viewHolder.tv_date.setText(list.get(position).getAddtime());
        String path = list.get(position).getIconurl();
        Picasso.with(context).load(HttpUtils.BASE_URL+path).into(viewHolder.imageView);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    class ViewHolder {
        @BindView(R.id.tv_kaice_name)
        TextView tv_name;
        @BindView(R.id.tv_kaice_company)
        TextView tv_compant;
        @BindView(R.id.tv_kaice_date)
        TextView tv_date;
        @BindView(R.id.tv_btn)
        TextView tv_btn;
        @BindView(R.id.img_kaice)
        ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
