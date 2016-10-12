package com.example.zwq.mygiftapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.adapter.MySearchAdapter;
import com.example.zwq.mygiftapp.bean.SearchInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.example.zwq.mygiftapp.utils.Share;
import com.example.zwq.mygiftapp.view.MySearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    @BindView(R.id.search_toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_search_content)
    Button btn_search_content;
    ListView listView;
    @BindView(R.id.search_view)
    MySearchView searchView;
    @BindView(R.id.search_progressBar)
    ProgressBar progress;
    String key = "";
    MySearchAdapter adapter;
    List<SearchInfo.ListBean> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.search_list);
        toolbar.setNavigationIcon(R.mipmap.back);
        listView.setOnItemClickListener(this);
        
        MyService service = HttpUtils.getMyService();
        service.getSearchInfo(key).enqueue(new Callback<SearchInfo>() {
            @Override
            public void onResponse(Call<SearchInfo> call, Response<SearchInfo> response) {
                SearchInfo searchInfo = response.body();
                list = searchInfo.getList();
                adapter = new MySearchAdapter(list, SearchActivity.this);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SearchInfo> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btn_search_content)
    void OnSearch() {
        key = searchView.getQuery().toString();
        progress.setVisibility(View.VISIBLE);
        if (key.equals("")) {
            Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
            progress.setVisibility(View.GONE);
        } else {
            MyService service = HttpUtils.getMyService();
            service.getSearchInfo(key).enqueue(new Callback<SearchInfo>() {
                @Override
                public void onResponse(Call<SearchInfo> call, Response<SearchInfo> response) {
                    SearchInfo searchInfo = response.body();
                    list = searchInfo.getList();
                    adapter = new MySearchAdapter(list, SearchActivity.this);
                    listView.setAdapter(adapter);
                    progress.setVisibility(View.GONE);
                }
                @Override
                public void onFailure(Call<SearchInfo> call, Throwable t) {
                    progress.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String id1 = list.get(position).getId();
        Intent intent = new Intent(SearchActivity.this,GiftListContentActivity.class);
        intent.putExtra("id",id1);
        startActivity(intent);
    }
}
