package com.by.framework.widget.pull.section;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.by.framework.core.BaseListAdapter;
import com.by.framework.widget.pull.FooterSpanSizeLookup;
import com.by.framework.widget.pull.ILayoutManager;

/**
 * Created by asus-pc on 2017/2/23.
 */

public class SectionGridLayoutManager extends GridLayoutManager implements ILayoutManager {


    public SectionGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SectionGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public SectionGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastVisiblePosition() {
        return findLastVisibleItemPosition();
    }

    @Override
    public void setUpAdapter(BaseListAdapter adapter) {
        FooterSpanSizeLookup lookup = new FooterSpanSizeLookup(adapter, getSpanCount());
        setSpanSizeLookup(lookup);
    }
}
