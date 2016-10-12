package com.example.zwq.mygiftapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.adapter.MyOpenContentPagerAdapter;
import com.example.zwq.mygiftapp.bean.OpenContentInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.example.zwq.mygiftapp.utils.Share;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class GameContentActivity extends AppCompatActivity {
    @BindView(R.id.open_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_open_description)
    TextView tv_description;
    @BindView(R.id.tv_open_name)
    TextView tv_name;
    @BindView(R.id.tv_open_type)
    TextView tv_type;
    @BindView(R.id.open_content_title)
    TextView tv_title;
    @BindView(R.id.tv_open_size)
    TextView tv_size;
    @BindView(R.id.img_open_icon)
    ImageView icon;
    @BindView(R.id.open_pager_content)
    ViewPager pager;
    @BindView(R.id.layout_container)
    LinearLayout layout_container;
    @BindView(R.id.gift_tv_btn)
    Button gift_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_fu);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);
        String id = getIntent().getStringExtra("id");
        MyService service = HttpUtils.getMyService();
        service.getOpenContent(id).enqueue(new Callback<OpenContentInfo>() {
            @Override
            public void onResponse(Call<OpenContentInfo> call, Response<OpenContentInfo> response) {
                OpenContentInfo info = response.body();
                String name = info.getApp().getName();
                String logo = info.getApp().getLogo();
                String type = info.getApp().getTypename();
                String appsize = info.getApp().getAppsize();
                String description = info.getApp().getDescription();
                final String download_addr = info.getApp().getDownload_addr();
                List<OpenContentInfo.ImgBean> imgList = info.getImg();
                List<View> list = new ArrayList<>();
                for (int i = 0; i <imgList.size() ; i++) {
                    String address = imgList.get(i).getAddress();
                    //view = LayoutInflater.from(GameContentActivity.this).inflate(R.layout.open_content_item,null);
                    //ImageView img = (ImageView) view.findViewById(R.id.open_content_img);
                    ImageView img = new ImageView(GameContentActivity.this);
                    img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Picasso.with(GameContentActivity.this).load(HttpUtils.BASE_URL+address).into(img);
                    list.add(img);
                }
                pager.setOffscreenPageLimit(2);
                pager.setPageMargin(10);
                layout_container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return pager.dispatchTouchEvent(event);
                    }
                });
                MyOpenContentPagerAdapter adapter= new MyOpenContentPagerAdapter(list);
                pager.setAdapter(adapter);
                if (appsize.equals("")){
                    tv_size.setText("大小:未知");
                }
                tv_size.setText("大小:"+appsize);
                if (download_addr.equals("")){
                    gift_btn.setText("暂无下载");
                    gift_btn.setBackgroundResource(R.color.colorDark);
                    gift_btn.setClickable(false);
                }else{
                    gift_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            Uri uri = Uri.parse(download_addr);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                }
                tv_title.setText(name);
                tv_name.setText(name);
                tv_description.setText("  "+description);
                tv_type.setText("类型:"+type);
                Picasso.with(GameContentActivity.this).load(HttpUtils.BASE_URL+logo).into(icon);
            }

            @Override
            public void onFailure(Call<OpenContentInfo> call, Throwable t) {

            }
        });
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
                Share.showShare(GameContentActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
