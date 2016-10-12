package com.example.zwq.qqfragment.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zwq.qqfragment.R;
import com.example.zwq.qqfragment.activity.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    ListView listView;
    List<String> list ;
    public MessageFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        list = new ArrayList<>();
        for (int i = 1; i <=20 ; i++) {
            list.add("小明"+i+"号");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        listView = (ListView) view.findViewById(R.id.list_message);
        TextView textView = new TextView(getActivity());
        textView.setText("暂无数据");
        ((ViewGroup)listView.getParent()).addView(textView);
        listView.setEmptyView(textView);
        textView.setGravity(Gravity.CENTER);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name",list.get(position));
                startActivity(intent);
            }
        });
        return view;
    }


}
