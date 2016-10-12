package com.example.zwq.qqfragment.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.zwq.qqfragment.R;
import com.example.zwq.qqfragment.adapter.MyAdapter;
import com.example.zwq.qqfragment.bean.Children;
import com.example.zwq.qqfragment.bean.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {
    ExpandableListView expandableListView;
    List<Group> list;

    public ContactsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        Group group1 = new Group();
        group1.setGroupName("我的好友");
        List<Children> children1 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Children c = new Children();
            c.setChildName("小明" + i + "号");
            children1.add(c);
        }
        group1.setList(children1);
        list.add(group1);

        Group group2 = new Group();
        group2.setGroupName("亲人");
        List<Children> children2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Children c1 = new Children();
            c1.setChildName("弟弟" + i + "号");
            children2.add(c1);
        }

        group2.setList(children2);
        list.add(group2);

        Group group3 = new Group();
        group3.setGroupName("朋友");
        List<Children> children3 = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Children c2 = new Children();
            c2.setChildName("帅哥" + i + "号");
            children3.add(c2);
        }
        group3.setList(children3);
        list.add(group3);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expand);
        MyAdapter adapter = new MyAdapter(list, getActivity());
        expandableListView.setAdapter(adapter);
        return view;

    }

}
