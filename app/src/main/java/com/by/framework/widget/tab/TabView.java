package com.by.framework.widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.by.framework.R;

/**
 * Created by asus-pc on 2017/2/20.
 */

public class TabView extends LinearLayout implements View.OnClickListener {

    private ImageView mTabImg1;
    private TextView mTabLabel1;

    public TabView(Context context) {
        super(context);
        setUpView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpView(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpView(context);
    }

    private void setUpView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_tab_view,this,true);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        mTabImg1 = (ImageView)findViewById(R.id.mTabImg1);
        mTabLabel1 = (TextView) findViewById(R.id.mTabLabel1);

        setOnClickListener(this);

    }

    public void setUpData(Tab tab){
        mTabImg1.setBackgroundResource(tab.imgResId);
        mTabLabel1.setText(tab.labelResId);
    }

    @Override
    public void onClick(View view) {

    }

    public static  class Tab{
        public int imgResId;
        public int labelResId;
        public int badgeCount;

        public Tab(int imgResId, int labelResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
        }

        public Tab(int imgResId, int labelResId, int badgeCount) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.badgeCount = badgeCount;
        }
    }
}
