package com.example.zwq.mygiftapp.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.GameContentActivity;
import com.example.zwq.mygiftapp.adapter.MyKaiceAdapter;
import com.example.zwq.mygiftapp.bean.KaiCeInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
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
public class KaiCeFragment extends Fragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.list_kaice)
    PullToRefreshListView list_kaice;
    List<KaiCeInfo.InfoBean> beanList;
    public KaiCeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kai_ce, container, false);
        ButterKnife.bind(KaiCeFragment.this,view);
        refresh();
        list_kaice.setMode(PullToRefreshBase.Mode.BOTH);
        list_kaice.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String gid = beanList.get(position).getGid();
        Intent intent = new Intent(getActivity(), GameContentActivity.class);
        intent.putExtra("id",gid);
        startActivity(intent);
    }

    private void refresh(){
        MyService service = HttpUtils.getMyService();
        service.getKaiCeList().enqueue(new Callback<KaiCeInfo>() {
            @Override
            public void onResponse(Call<KaiCeInfo> call, Response<KaiCeInfo> response) {
                KaiCeInfo info = response.body();
                beanList = info.getInfo();
                MyKaiceAdapter adapter = new MyKaiceAdapter(beanList,getActivity());
                list_kaice.setAdapter(adapter);
                list_kaice.setOnItemClickListener(KaiCeFragment.this);
                list_kaice.onRefreshComplete();
            }
            @Override
            public void onFailure(Call<KaiCeInfo> call, Throwable t) {

            }
        });
    }
}
