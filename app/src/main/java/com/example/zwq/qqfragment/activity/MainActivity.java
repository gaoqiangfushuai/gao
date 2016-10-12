package com.example.zwq.qqfragment.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.zwq.qqfragment.R;
import com.example.zwq.qqfragment.fragment.ContactsFragment;
import com.example.zwq.qqfragment.fragment.DynamicFragment;
import com.example.zwq.qqfragment.fragment.MessageFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    RadioGroup group;
    FragmentManager fragmentManager;
    MessageFragment messageFragment;
    ContactsFragment contactsFragment;
    DynamicFragment dynamicFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = (RadioGroup) findViewById(R.id.rg);
        group.setOnCheckedChangeListener(this);
//        if (messageFragment==null){
//            messageFragment = new MessageFragment();
//        }
//        if (contactsFragment==null){
//            contactsFragment = new ContactsFragment();
//        }
//        if (dynamicFragment==null){
//            dynamicFragment = new DynamicFragment();
//        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
//        ft.add(R.id.container,messageFragment);
//        ft.add(R.id.container,contactsFragment).hide(contactsFragment);
//        ft.add(R.id.container,dynamicFragment).hide(dynamicFragment);
        ft.replace(R.id.container,new MessageFragment());
        ft.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction ft=fragmentManager.beginTransaction();
//        ft.hide(messageFragment);
//        ft.hide(contactsFragment);
//        ft.hide(dynamicFragment);
        switch (checkedId){
            case R.id.rb_message:
                //ft.show(messageFragment);
                ft.replace(R.id.container,new MessageFragment());
                break;
            case R.id.rb_contacts:
                //ft.show(contactsFragment);
                ft.replace(R.id.container,new ContactsFragment());
                break;
            case R.id.rb_dynamic:
                //ft.show(dynamicFragment);
                ft.replace(R.id.container,new DynamicFragment());
                break;
        }
        ft.commit();
    }
}
