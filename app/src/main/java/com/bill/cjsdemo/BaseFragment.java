package com.bill.cjsdemo;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootLayout(), container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    protected abstract @LayoutRes
    int getRootLayout();

    protected abstract void initView(View rootView);

    protected void initData() {

    }

    protected void $Log(String msg) {
        Log.e("Bill", msg);
    }

}