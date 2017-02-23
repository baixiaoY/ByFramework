package com.by.framework.widget.pull;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.by.framework.core.BaseListActivity;
import com.by.framework.core.BaseListAdapter;

/**
 * Created by asus-pc on 2017/2/20.
 */

public class FooterSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private BaseListAdapter adapter;
    private int spanCount;

    public FooterSpanSizeLookup(BaseListAdapter adapter, int spanCount) {
        this.adapter = adapter;
        this.spanCount = spanCount;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isFooterView(position)||adapter.isSectionHeader(position)){
            return spanCount;
        }
        return 1;
    }
}
