package com.example.zwq.mygiftapp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zwq.mygiftapp.R;
import com.example.zwq.mygiftapp.fragment.GiftFragment;
import com.example.zwq.mygiftapp.fragment.HotFragment;
import com.example.zwq.mygiftapp.fragment.OpenFragment;
import com.example.zwq.mygiftapp.fragment.SpecialFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rg)
    RadioGroup radioGroup;
    @BindView(R.id.btn_gift)
    RadioButton btn_gift;
    @BindView(R.id.btn_open)
    RadioButton btn_open;
    @BindView(R.id.btn_hot)
    RadioButton btn_hot;
    @BindView(R.id.btn_special)
    RadioButton btn_special;
    @BindView(R.id.sliding)
    SlidingPaneLayout slidingPaneLayout;
    @BindView(R.id.linear_content)
    LinearLayout linear_content;
    @BindView(R.id.layout_content)
    RelativeLayout layout_content;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_gift_title)
    TextView textView;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn_search)
    Button btn_search;

    GiftFragment giftFragment;
    OpenFragment openFragment;
    HotFragment hotFragment;
    SpecialFragment specialFragment;
    Fragment currentFragment;
    private long exitTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        addFragment();
        toolbar.setTitle( "");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.title_bar_menu);
        textView.setText("礼包精灵");
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                linear_content.setScaleY(1-slideOffset*0.4f);
                linear_content.setScaleX(1-slideOffset*0.4f);
            }

            @Override
            public void onPanelOpened(View panel) {

            }
            @Override
            public void onPanelClosed(View panel) {

            }
        });
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initFragment() {
    if (giftFragment==null){
        giftFragment = new GiftFragment();
    }
    if (openFragment==null){
        openFragment = new OpenFragment();
    }
    if (hotFragment==null){
        hotFragment = new HotFragment();
    }
    if (specialFragment==null){
        specialFragment = new SpecialFragment();
    }
}
    private void addFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container,giftFragment).commit();
        currentFragment = giftFragment;
    }
    private void switchFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()){
            ft.hide(currentFragment).show(fragment).commit();
        }else{
            ft.hide(currentFragment).add(R.id.container,fragment).commit();
        }
        currentFragment = fragment;
    }
    @OnClick(R.id.btn_gift)
    void giftClick(){
        toolbar.setTitle("");
        textView.setText("礼包精灵");
        btn_search.setVisibility(View.VISIBLE);
        switchFragment(giftFragment);
    }
    @OnClick(R.id.btn_open)
    void openClick(){
        toolbar.setTitle("");
        textView.setText("开服");
        btn_search.setVisibility(View.GONE);
        switchFragment(openFragment);
    }
    @OnClick(R.id.btn_hot)
    void hotClick(){
        toolbar.setTitle("");
        textView.setText("热门游戏");
        btn_search.setVisibility(View.GONE);
        switchFragment(hotFragment);
    }
    @OnClick(R.id.btn_special)
    void specialClick(){
        toolbar.setTitle("");
        textView.setText("独家企划");
        btn_search.setVisibility(View.GONE);
        switchFragment(specialFragment);
    }

    @OnClick(R.id.btn1)
    void onHome(){
        slidingPaneLayout.closePane();
    }
    @OnClick(R.id.btn2)
    void onMyGift(){
    }
    @OnClick(R.id.btn3)
    void onUs(){
    }
    @OnClick(R.id.btn4)
    void onSet(){
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (slidingPaneLayout.isOpen()){
                    slidingPaneLayout.closePane();
                }else{
                    slidingPaneLayout.openPane();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis() - exitTime)>2000){
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
