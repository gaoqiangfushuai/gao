package com.example.zwq.mygiftapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.SpecialOneActivity;
import com.example.zwq.mygiftapp.adapter.MySpecialOnAdapter;
import com.example.zwq.mygiftapp.bean.SpecialOneInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOneFragment extends Fragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.list_wen)
    PullToRefreshListView list_wen;
    int page = 0;
    List<SpecialOneInfo.ListBean> list;
    public SpecialOneFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_one, container, false);
        ButterKnife.bind(SpecialOneFragment.this,view);
        refresh();
        list_wen.setOnItemClickListener(SpecialOneFragment.this);
        return view;
    }
    private void refresh(){
        MyService service = HttpUtils.getMyService();
        service.getSpecialOneInfo(page).enqueue(new Callback<SpecialOneInfo>() {
            @Override
            public void onResponse(Call<SpecialOneInfo> call, Response<SpecialOneInfo> response) {
                SpecialOneInfo info = response.body();
                list = info.getList();
                MySpecialOnAdapter adapter = new MySpecialOnAdapter(list,getActivity());
                list_wen.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<SpecialOneInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), SpecialOneActivity.class);
        String path = list.get(position-1).getIconurl();
        int sid = list.get(position-1).getSid();
        String name = list.get(position-1).getName();
        String content = list.get(position-1).getDescs();
        intent.putExtra("path",path);
        intent.putExtra("id",sid);
        intent.putExtra("title",name);
        intent.putExtra("content",content);
        startActivity(intent);
    }
}
