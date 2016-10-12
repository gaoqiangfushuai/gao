package com.example.zwq.mygiftapp.fragment;


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
public class SpecialFragment extends Fragment {
    @BindView(R.id.special_tab)
    TabLayout tab;
    @BindView(R.id.special_pager)
    ViewPager special_pager;
    String [] titles = new String[]{"暴打星期三","新游周刊"};

    public SpecialFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special, container, false);
        ButterKnife.bind(SpecialFragment.this,view);
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorGreen));
        tab.setTabTextColors(getResources().getColor(R.color.colorGreen),getResources().getColor(R.color.colorGreen));
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SpecialOneFragment());
        fragmentList.add(new SpeicalTwoFragment());
        for (int i = 0; i <titles.length; i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
            //tab.getTabAt(i).setText(titles[i]);
        }
        MyOpenPagerAdapter adapter = new MyOpenPagerAdapter(getFragmentManager(),fragmentList,titles);
        special_pager.setAdapter(adapter);
        tab.setupWithViewPager(special_pager);
        return view;
    }

}
