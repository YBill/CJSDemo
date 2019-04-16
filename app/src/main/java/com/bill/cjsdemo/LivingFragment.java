package com.bill.cjsdemo;

import android.view.View;

public class LivingFragment extends BaseFragment {

    public static LivingFragment newInstance() {
        LivingFragment fragment = new LivingFragment();
        return fragment;
    }

    @Override
    protected int getRootLayout() {
        return R.layout.content_scrolling;
    }

    @Override
    protected void initView(View rootView) {

    }
}
