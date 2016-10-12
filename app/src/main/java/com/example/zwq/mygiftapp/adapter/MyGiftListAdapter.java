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
import com.example.zwq.mygiftapp.bean.GiftListInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhuwenqiang on 2016/9/26.
 */
public class MyGiftListAdapter extends BaseAdapter {
    List<GiftListInfo.ListBean> list;
    Context context;

    public void setList(List<GiftListInfo.ListBean> list) {
        this.list = list;
    }
    public MyGiftListAdapter(List<GiftListInfo.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public GiftListInfo.ListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gift_list_item,null);
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
        }else{
            viewHolder.tv_btn.setText("立即领取");
        }
        viewHolder.tv_date.setText("时间:"+list.get(position).getAddtime());
        String path = HttpUtils.BASE_URL+list.get(position).getIconurl();
        Picasso.with(context).load(path).into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.tv_gift_name)
        TextView tv_name;
        @BindView(R.id.tv_gift_giftName)
        TextView tv_giftName;
        @BindView(R.id.tv_gift_num)
        TextView tv_num;
        @BindView(R.id.tv_gift_date)
        TextView tv_date;
        @BindView(R.id.tv_btn)
        Button tv_btn;
        @BindView(R.id.img_gift_list)
        ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
