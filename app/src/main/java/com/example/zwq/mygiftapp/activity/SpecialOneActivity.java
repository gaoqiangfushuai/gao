package com.example.zwq.mygiftapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.adapter.MySpecialOneContentAdapter;
import com.example.zwq.mygiftapp.bean.SpecialOneContentInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.example.zwq.mygiftapp.utils.Share;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialOneActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    @BindView(R.id.special_one_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_special_one_desc)
    TextView tv_desc;
    @BindView(R.id.special_one_grid)
    GridView gridView;
    @BindView(R.id.special_one_title)
    TextView tv_title;
    @BindView(R.id.special_one_content_img)
    ImageView img;
    List<SpecialOneContentInfo.ListBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_one);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        String content = getIntent().getStringExtra("content");
        String path = getIntent().getStringExtra("path");
        String title = getIntent().getStringExtra("title");
        int id = getIntent().getIntExtra("id", 0);
        tv_desc.setText("导读："+content);
        tv_title.setText(title);
        Picasso.with(this).load(HttpUtils.BASE_URL+path).into(img);
        MyService service = HttpUtils.getMyService();
        service.getSpecialOneContentInfo(id).enqueue(new Callback<SpecialOneContentInfo>() {
            @Override
            public void onResponse(Call<SpecialOneContentInfo> call, Response<SpecialOneContentInfo> response) {
                SpecialOneContentInfo info = response.body();
                list = info.getList();
                MySpecialOneContentAdapter adapter = new MySpecialOneContentAdapter(list,SpecialOneActivity.this);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(SpecialOneActivity.this);
            }
            @Override
            public void onFailure(Call<SpecialOneContentInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        String appid = list.get(position).getAppid();
        intent.setClass(this,GameContentActivity.class);
        intent.putExtra("id", appid);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gift,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.gift_share:
                Share.showShare(SpecialOneActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
