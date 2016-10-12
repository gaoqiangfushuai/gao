package com.example.zwq.mygiftapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.GameContentActivity;
import com.example.zwq.mygiftapp.bean.SpecialTwoContentInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhuwenqiang on 2016/10/9.
 */
public class MySpecialTwoContentAdapter extends BaseAdapter {
    List<SpecialTwoContentInfo.ListBean> list;
    Context context;

    public MySpecialTwoContentAdapter(List<SpecialTwoContentInfo.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SpecialTwoContentInfo.ListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.special_two_content_item,null);
            viewHolder = new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getAppname());
        viewHolder.tv_desc.setText(list.get(position).getDescs());
        viewHolder.tv_type.setText("类型:"+list.get(position).getTypename());
        viewHolder.tv_size.setText("大小:"+list.get(position).getAppsize());
        String path = HttpUtils.BASE_URL+list.get(position).getIconurl();
        Picasso.with(context).load(path).into(viewHolder.imageView);
        viewHolder.tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appid = list.get(position).getAppid();
                Intent intent = new Intent(context, GameContentActivity.class);
                intent.putExtra("id",appid);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder {
        @BindView(R.id.tv_special_two_name)
        TextView tv_name;
        @BindView(R.id.tv_special_two_type)
        TextView tv_type;
        @BindView(R.id.tv_special_two_size)
        TextView tv_size;
        @BindView(R.id.tv_special_two_desc)
        TextView tv_desc;
        @BindView(R.id.tv_special_two_btn)
        Button tv_btn;
        @BindView(R.id.img_special_two_list)
        ImageView imageView;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
