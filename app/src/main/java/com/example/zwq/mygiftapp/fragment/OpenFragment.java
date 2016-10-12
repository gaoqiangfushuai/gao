package com.example.zwq.mygiftapp.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.adapter.MyOpenPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenFragment extends Fragment {
    @BindView(R.id.open_tab)
    TabLayout tab;
    @BindView(R.id.open_pager)
    ViewPager open_pager;
    String [] titles = new String[]{"开服","开测"};

    public OpenFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open, container, false);
        ButterKnife.bind(OpenFragment.this,view);
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorGreen));
        tab.setTabTextColors(getResources().getColor(R.color.colorGreen),getResources().getColor(R.color.colorGreen));
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new KaiFuFragment());
        fragmentList.add(new KaiCeFragment());
        for (int i = 0; i <titles.length; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
            //tab.getTabAt(i).setText(titles[i]);
        }
        MyOpenPagerAdapter adapter = new MyOpenPagerAdapter(getFragmentManager(),fragmentList,titles);
        open_pager.setAdapter(adapter);
        tab.setupWithViewPager(open_pager);
        return view;
    }

}
