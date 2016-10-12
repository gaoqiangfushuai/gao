package com.example.zwq.mygiftapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.activity.GiftListContentActivity;
import com.example.zwq.mygiftapp.adapter.MyGiftAdAdapter;
import com.example.zwq.mygiftapp.adapter.MyGiftListAdapter;
import com.example.zwq.mygiftapp.bean.GiftListInfo;
import com.example.zwq.mygiftapp.http.HttpUtils;
import com.example.zwq.mygiftapp.http.MyService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment extends Fragment implements AbsListView.OnScrollListener,View.OnClickListener,AdapterView.OnItemClickListener {
    AutoScrollViewPager vp_ad;
    ListView gift_list;
    LinearLayout layout_ad;
    ProgressBar progressBar;
    int page = 1;
    List<View> viewList;
    List<GiftListInfo.ListBean> list;
    MyGiftListAdapter adapter;
    Button btn_load;
    public GiftFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        gift_list = (ListView) view.findViewById(R.id.gift_list);
        list = new ArrayList<>();
        MyService service = HttpUtils.getMyService();
        service.getGiftList(page).enqueue(new Callback<GiftListInfo>() {
            @Override
            public void onResponse(Call<GiftListInfo> call, Response<GiftListInfo> response) {
                GiftListInfo info = response.body();
                initAd(info);
                vp_ad.startAutoScroll();
                vp_ad.setBorderAnimation(true);
                vp_ad.setCycle(true);
                vp_ad.setInterval(3000);
                vp_ad.setDirection(AutoScrollViewPager.RIGHT);
                vp_ad.setStopScrollWhenTouch(true);
                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.footer_item,null);
                progressBar = (ProgressBar) view2.findViewById(R.id.progressBar);
                btn_load = (Button) view2.findViewById(R.id.btn_load);
                gift_list.addFooterView(view2);
                list = info.getList();
                adapter = new MyGiftListAdapter(list, getActivity());
                gift_list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GiftListInfo> call, Throwable t) {
            }
        });
        gift_list.setOnScrollListener(GiftFragment.this);
        gift_list.setOnItemClickListener(GiftFragment.this);
        return view;
    }
    private void initAd(GiftListInfo info) {
        final List<GiftListInfo.AdBean> adBeanList = info.getAd();
        viewList = new ArrayList<>();
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.header_item, null);
        vp_ad = (AutoScrollViewPager) view1.findViewById(R.id.gift_vp_ad);
        layout_ad = (LinearLayout) view1.findViewById(R.id.layout_point);
        for (int i = 0; i < adBeanList.size(); i++) {
            final int pos = i;
            ImageView image = new ImageView(getActivity());
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent();
                    intent.setClass(getActivity(), GiftListContentActivity.class);
                    String giftId = adBeanList.get(pos).getGiftid();
                    intent.putExtra("id",giftId);
                    startActivity(intent);
                }
            });
            String picUrl = adBeanList.get(i).getIconurl();
            Picasso.with(getActivity()).load(HttpUtils.BASE_URL + picUrl).into(image);
            viewList.add(image);
            ImageView image_point = new ImageView(getActivity());
            image_point.setPadding(10, 0, 10, 0);
            if (i == 0) {
                image_point.setImageResource(R.drawable.ad_point_select);
            } else {
                image_point.setImageResource(R.drawable.ad_point_default);
            }
            layout_ad.addView(image_point);
        }
        MyGiftAdAdapter adapter = new MyGiftAdAdapter(viewList);
        vp_ad.setAdapter(adapter);
        gift_list.addHeaderView(view1);
        vp_ad.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int count = layout_ad.getChildCount();
                for (int i = 0; i < count; i++) {
                    ImageView img = (ImageView) layout_ad.getChildAt(i);
                    if (position == i) {
                        img.setImageResource(R.drawable.ad_point_select);
                    } else {
                        img.setImageResource(R.drawable.ad_point_default);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void measureHeight() {
        int count = gift_list.getAdapter().getCount();
        int totalItemHeight = 0;
        for (int i = 0; i < count; i++) {
            View view = gift_list.getAdapter().getView(i, null, gift_list);
            Log.e("===", "====");
            view.measure(0, 0);
            totalItemHeight += view.getMeasuredHeight();
        }
        int dividerHeight = gift_list.getDividerHeight();
        int totalDividerHeight = dividerHeight * (count - 1);
        int totalHeight = totalDividerHeight + totalItemHeight;
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, totalHeight
        );
        gift_list.setLayoutParams(p);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
            btn_load.setOnClickListener(GiftFragment.this);
        }
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_load:
                btn_load.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                page++;
                MyService service = HttpUtils.getMyService();
                service.getGiftList(page).enqueue(new Callback<GiftListInfo>() {
                    @Override
                    public void onResponse(Call<GiftListInfo> call, Response<GiftListInfo> response) {
                        GiftListInfo info = response.body();
                        List<GiftListInfo.ListBean> list2 = info.getList();
                        list.addAll(list2);
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        btn_load.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onFailure(Call<GiftListInfo> call, Throwable t) {
                    }
                });
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent  = new Intent();
        intent.setClass(getActivity(), GiftListContentActivity.class);
        String giftId = list.get(position-1).getId();
        intent.putExtra("id",giftId);
        startActivity(intent);
    }

}
