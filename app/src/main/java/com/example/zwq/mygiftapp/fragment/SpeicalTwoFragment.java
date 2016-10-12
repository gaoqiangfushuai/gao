package com.example.zwq.mygiftapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.SpecialTwoActivity;
import com.example.zwq.mygiftapp.adapter.MySpecialTwoAdapter;
import com.example.zwq.mygiftapp.bean.SpecialOneInfo;
import com.example.zwq.mygiftapp.bean.SpecialTwoInfo;
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
public class SpeicalTwoFragment extends Fragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.list_two)
    PullToRefreshListView list_two;
    int page = 0;
    List<SpecialTwoInfo.ListBean> list;

    public SpeicalTwoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speical_two, container, false);
        ButterKnife.bind(SpeicalTwoFragment.this,view);
        refresh();
        list_two.setOnItemClickListener(SpeicalTwoFragment.this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int id1 = list.get(position-1).getId();
        String authorimg = list.get(position-1).getAuthorimg();
        String iconurl = list.get(position-1).getIconurl();
        String descs = list.get(position-1).getDescs();
        String name = list.get(position-1).getName();
        Intent intent = new Intent(getActivity(), SpecialTwoActivity.class);
        intent.putExtra("id",id1);
        intent.putExtra("content",descs);
        intent.putExtra("authorimg",authorimg);
        intent.putExtra("iconurl",iconurl);
        intent.putExtra("title",name);
        startActivity(intent);
    }
    private void refresh(){
        MyService service = HttpUtils.getMyService();
        service.getSpecialTwoInfo(page).enqueue(new Callback<SpecialTwoInfo>() {
            @Override
            public void onResponse(Call<SpecialTwoInfo> call, Response<SpecialTwoInfo> response) {
                SpecialTwoInfo body = response.body();
                list = body.getList();
                MySpecialTwoAdapter adapter = new MySpecialTwoAdapter(list,getActivity());
                list_two.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SpecialTwoInfo> call, Throwable t) {

            }
        });
    }
}
