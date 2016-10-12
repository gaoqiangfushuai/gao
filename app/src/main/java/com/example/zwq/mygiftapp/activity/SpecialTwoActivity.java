package com.example.zwq.mygiftapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.adapter.MySpecialTwoContentAdapter;
import com.example.zwq.mygiftapp.bean.SpecialTwoContentInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.example.zwq.mygiftapp.utils.Share;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialTwoActivity extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    ImageView img_content,img_author;
    TextView tv_title,tv_desc;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_two);
        listView = (ListView) findViewById(R.id.list_two_content);
        id = getIntent().getIntExtra("id", 0);
        initHeader();
        refresh();
    }
    public void refresh(){
        MyService service = HttpUtils.getMyService();
        service.getSpecialTwoContentInfo(id).enqueue(new Callback<SpecialTwoContentInfo>() {
            @Override
            public void onResponse(Call<SpecialTwoContentInfo> call, Response<SpecialTwoContentInfo> response) {
                SpecialTwoContentInfo info = response.body();
                List<SpecialTwoContentInfo.ListBean> list = info.getList();
                MySpecialTwoContentAdapter adapter = new MySpecialTwoContentAdapter(list,SpecialTwoActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SpecialTwoContentInfo> call, Throwable t) {

            }
        });
    }
    public void initHeader(){
        String content = getIntent().getStringExtra("content");
        String authorimg = getIntent().getStringExtra("authorimg");
        String iconurl = getIntent().getStringExtra("iconurl");
        String title = getIntent().getStringExtra("title");
        View view = LayoutInflater.from(this).inflate(R.layout.special_two_header_item,null);
        toolbar= (Toolbar) view.findViewById(R.id.special_two_toolbar);
        img_content= (ImageView) view.findViewById(R.id.special_two_header_img);
        tv_title= (TextView) view.findViewById(R.id.special_two_header_title);
        tv_desc= (TextView) view.findViewById(R.id.special_two_header_desc);
        img_author= (ImageView) view.findViewById(R.id.special_two_header_img_author);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        tv_title.setText(title);
        tv_desc.setText(content);
        Picasso.with(this).load(HttpUtils.BASE_URL+iconurl).into(img_content);
        Picasso.with(this).load(HttpUtils.BASE_URL+"/"+authorimg).into(img_author);
        listView.addHeaderView(view);
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
                Share.showShare(SpecialTwoActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
