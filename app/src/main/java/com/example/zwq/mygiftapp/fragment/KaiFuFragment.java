package com.example.zwq.mygiftapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.GameContentActivity;
import com.example.zwq.mygiftapp.adapter.MyKaiFuAdapter;
import com.example.zwq.mygiftapp.bean.Group;
import com.example.zwq.mygiftapp.bean.KaiFuInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaiFuFragment extends Fragment implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {
    ExpandableListView list_kaifu;
    List<KaiFuInfo.InfoBean> infoList;

    public KaiFuFragment() {

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kai_fu, container, false);
        list_kaifu = (ExpandableListView) view.findViewById(R.id.kaifu_list);
        MyService service = HttpUtils.getMyService();
        service.getKaiFuList().enqueue(new Callback<KaiFuInfo>() {
            @Override
            public void onResponse(Call<KaiFuInfo> call, Response<KaiFuInfo> response) {
                KaiFuInfo info = response.body();
                infoList = info.getInfo();
                List<Group> groupList = new ArrayList<>();
                int index = -1;
                String addtime = null;
                for (int i = 0; i < infoList.size(); i++) {
                    KaiFuInfo.InfoBean infoBean = infoList.get(i);
                    if (!((infoList.get(i).getAddtime()).equals(addtime))) {
                        Group group = new Group();
                        index++;
                        addtime = infoList.get(i).getAddtime();
                        List<KaiFuInfo.InfoBean> list = new ArrayList<>();
                        group.setGroupName(addtime);
                        group.setList(list);
                        groupList.add(group);
                    }
                    groupList.get(index).getList().add(infoBean);
                }
                MyKaiFuAdapter adapter = new MyKaiFuAdapter(groupList, getActivity());
                list_kaifu.setAdapter(adapter);
                int count = list_kaifu.getCount();
                for (int i = 0; i < count; i++) {
                    list_kaifu.expandGroup(i);
                }
                list_kaifu.setOnChildClickListener(KaiFuFragment.this);
                list_kaifu.setOnGroupClickListener(KaiFuFragment.this);
            }

            @Override
            public void onFailure(Call<KaiFuInfo> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent intent = new Intent(getActivity(), GameContentActivity.class);
        String gid = infoList.get(childPosition).getGid();
        intent.putExtra("id", gid);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }
}
