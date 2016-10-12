package com.example.zwq.mygiftapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhuwenqiang on 2016/10/4.
 */
public class MyOpenPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list ;
    String [] titles;

    public MyOpenPagerAdapter(FragmentManager fm,List<Fragment> list,String []titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }
}
