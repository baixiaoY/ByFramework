package com.by.framework.simple;

import com.by.framework.R;
import com.by.framework.core.BaseActivity;

/**
 * Created by asus-pc on 2017/2/22.
 */

public class SampleListActivity extends BaseActivity {

    private SampleListFragment mSampleListFragment;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_sample_list_1, R.string.title_recycler_fragment);
    }

    @Override
    protected void setUpView() {
        mSampleListFragment = new SampleListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mSampleListFragmentLayout, mSampleListFragment).commit();
    }

    @Override
    protected void setUpData() {

    }
}
