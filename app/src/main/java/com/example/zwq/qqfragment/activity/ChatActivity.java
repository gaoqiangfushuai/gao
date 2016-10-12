package com.example.zwq.qqfragment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zwq.qqfragment.R;
import com.example.zwq.qqfragment.adapter.MyChatAdapte;
import com.example.zwq.qqfragment.bean.ChatInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    TextView textView;
    EditText editText;
    List<ChatInfo> list;
    MyChatAdapte adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listView = (ListView) findViewById(R.id.list_chat);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.tv_chat_name);
        editText = (EditText) findViewById(R.id.editText);
        list = new ArrayList<>();
        final String name = getIntent().getStringExtra("name")+"：";
        textView.setText(name);
        adapter = new MyChatAdapte(list,this);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatInfo chatInfo = new ChatInfo();
                String content = editText.getText().toString();
                editText.setText("");
                chatInfo.setContent(content);
                Random random = new Random();
                boolean flag = random.nextBoolean();
                if (flag==true){
                    chatInfo.setFlag(flag);
                    chatInfo.setName("强哥：");
                }else{
                    chatInfo.setFlag(flag);
                    chatInfo.setName(name);
                }
                list.add(chatInfo);
                adapter = new MyChatAdapte(list,ChatActivity.this);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
