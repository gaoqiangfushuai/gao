package com.example.zwq.mygiftapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.GameContentActivity;
import com.example.zwq.mygiftapp.adapter.MyHotGridAdapter;
import com.example.zwq.mygiftapp.adapter.MyHotListAdapter;
import com.example.zwq.mygiftapp.bean.HotInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    @BindView(R.id.list_competitive)
    ListView list_competitive;
    @BindView(R.id.grid_hot)
    GridView grid_hot;
    HotInfo hotInfo;

    public HotFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(HotFragment.this, view);
        MyService service = HttpUtils.getMyService();
        service.getHotInfo().enqueue(new Callback<HotInfo>() {
            @Override
            public void onResponse(Call<HotInfo> call, Response<HotInfo> response) {
                hotInfo = response.body();
                List<HotInfo.InfoBean.Push1Bean> list1 = hotInfo.getInfo().getPush1();
                MyHotListAdapter adapter = new MyHotListAdapter(list1, getActivity());
                list_competitive.setAdapter(adapter);
                List<HotInfo.InfoBean.Push2Bean> list2 = hotInfo.getInfo().getPush2();
                MyHotGridAdapter adapter2 = new MyHotGridAdapter(list2, getActivity());
                grid_hot.setAdapter(adapter2);
                list_competitive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        String id1 = hotInfo.getInfo().getPush1().get(position).getAppid();
                        intent.setClass(getActivity(), GameContentActivity.class);
                        intent.putExtra("id", id1);
                        startActivity(intent);
                    }
                });
                grid_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent();
                        String id2 = hotInfo.getInfo().getPush2().get(position).getAppid();
                        intent.setClass(getActivity(), GameContentActivity.class);
                        intent.putExtra("id", id2);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<HotInfo> call, Throwable t) {

            }
        });
        return view;
    }
}
