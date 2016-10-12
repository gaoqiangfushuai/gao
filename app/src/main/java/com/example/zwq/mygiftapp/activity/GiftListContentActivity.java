package com.example.zwq.mygiftapp.activity;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.adapter.MyGiftListAdapter;
import com.example.zwq.mygiftapp.bean.GiftListContent;
import com.example.zwq.mygiftapp.bean.GiftListInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.example.zwq.mygiftapp.utils.Share;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiftListContentActivity extends AppCompatActivity {
    @BindView(R.id.gift_tv_explains)
    TextView tv_explains;
    @BindView(R.id.gift_content_img)
    CircleImageView img_content;
    @BindView(R.id.gift_tv_descs)
    TextView tv_descs;
    @BindView(R.id.gift_over_time)
    TextView tv_time;
    @BindView(R.id.gift_exchange_num)
    TextView tv_num;
    @BindView(R.id.gift_content_title)
    TextView tv_title;
    @BindView(R.id.gift_tv_btn)
    TextView tv_btn;
    @BindView(R.id.gift_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list_content);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.back);
        setSupportActionBar(toolbar);
        String id = getIntent().getStringExtra("id");
        MyService service = HttpUtils.getMyService();
        service.getGiftContent(id).enqueue(new Callback<GiftListContent>() {
            @Override
            public void onResponse(Call<GiftListContent> call, Response<GiftListContent> response) {
                GiftListContent content = response.body();
                String explains = content.getInfo().getExplains();
                int num = content.getInfo().getExchanges();
                String overtime = content.getInfo().getOvertime();
                String descs = content.getInfo().getDescs();
                String iconurl = content.getInfo().getIconurl();
                String gname = content.getInfo().getGname();
                String giftname = content.getInfo().getGiftname();
                if(num==0){
                    tv_btn.setText("马上淘号");
                }
                tv_title.setText(gname+"-"+giftname);
                tv_explains.setText(explains);
                tv_descs.setText(descs);
                tv_time.setText("有效期:"+overtime);
                tv_num.setText("礼包剩余:"+num);
                Picasso.with(GiftListContentActivity.this).load(HttpUtils.BASE_URL+iconurl).into(img_content);
            }

            @Override
            public void onFailure(Call<GiftListContent> call, Throwable t) {
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
                Share.showShare(GiftListContentActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
