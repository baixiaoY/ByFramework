package com.by.framework.simple;

import com.by.framework.R;
import com.by.framework.core.BaseActivity;
import com.by.framework.widget.tab.TabLayout;
import com.by.framework.widget.tab.TabView;

import java.util.ArrayList;

/**
 * Created by asus-pc on 2017/2/20.
 */

public class sampleTabActivity extends BaseActivity implements TabLayout.OnTabClickListener {


    private TabLayout mTabLayout;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_tab,R.string.tab);
    }

    @Override
    protected void setUpView() {
        mTabLayout = (TabLayout)findViewById(R.id.mTabLayout);
    }

    @Override
    protected void setUpData() {
        ArrayList<TabView.Tab> tabs = new ArrayList<>();
        tabs.add(new TabView.Tab(R.mipmap.ic_build,R.string.tab1));
        tabs.add(new TabView.Tab(R.mipmap.ic_explore,R.string.tab2));
        tabs.add(new TabView.Tab(R.mipmap.ic_extension,R.string.tab3));
        tabs.add(new TabView.Tab(R.mipmap.ic_explore,R.string.tab4));
        mTabLayout.setUpData(tabs,this);

    }

    @Override
    public void onTabClick(TabView.Tab index) {

    }
}
