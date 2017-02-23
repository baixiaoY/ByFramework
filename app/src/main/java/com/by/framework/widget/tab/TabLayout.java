package com.by.framework.widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/20.
 */

public class TabLayout extends LinearLayout implements View.OnClickListener {


    private ArrayList<TabView.Tab> tabs;
    private OnTabClickListener listener;

    public TabLayout(Context context) {
        super(context);
        setUpView(context);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    private void setUpView(Context context) {
        setOrientation(HORIZONTAL);
    }

    public void setUpData(ArrayList<TabView.Tab> tabs,OnTabClickListener listener){
        this.tabs = tabs;
        this.listener = listener;
        if (tabs != null && tabs.size()>0){
            TabView mTabView = null;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            for (int i = 0; i < tabs.size(); i++) {
                mTabView = new TabView(getContext());
                mTabView.setTag(tabs.get(i));
                mTabView.setUpData(tabs.get(i));
                mTabView.setOnClickListener(this);
                addView(mTabView,params);
            }
        }else{
            throw new IllegalArgumentException("tabs can't be empty");
        }
    }

    @Override
    public void onClick(View view) {
        listener.onTabClick((TabView.Tab)view.getTag());
    }

    public interface OnTabClickListener{
        void onTabClick(TabView.Tab tab);
    }
}
