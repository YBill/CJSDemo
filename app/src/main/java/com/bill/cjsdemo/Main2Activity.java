package com.bill.cjsdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private BaseFragment[] fragments = new BaseFragment[4];
    private BaseFragment currentFragment;
    private TextView[] bottomText = new TextView[4];

    private GestureDetector mDetector;
    private boolean topIsShow = true;
    private int touchSlop;

    private int topSearchHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setStatusBarColor(R.color.white, true);

        bottomText[0] = findViewById(R.id.tv_1);
        bottomText[1] = findViewById(R.id.tv_2);
        bottomText[2] = findViewById(R.id.tv_3);
        bottomText[3] = findViewById(R.id.tv_4);

        ViewGroup group = findViewById(R.id.fl_bottom);
        for (int i = 0; i < group.getChildCount(); i++) {
            group.getChildAt(i).setOnClickListener(this);
        }

        fragments[0] = HomeFragment.newInstance();
        fragments[1] = PeoNumFragment.newInstance();
        fragments[2] = LivingFragment.newInstance();
        fragments[3] = MineFragment.newInstance();

        currentFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame);
        if (currentFragment == null) {
            currentFragment = fragments[0];
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_home, currentFragment);
        transaction.commit();

        touchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        topSearchHeight = (int) getResources().getDimension(R.dimen.top_height);

        gestureDetector();

    }

    private void setStatusBarColor(@ColorRes int statusBarColor, boolean isDarkFont) {
        ImmersionBar
                .with(this)
                .fitsSystemWindows(true)
                .statusBarColor(statusBarColor)
                .statusBarDarkFont(isDarkFont, 1.0f)
                .init();
    }

    private void switchContent(BaseFragment from, BaseFragment to) {
        currentFragment = to;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (to instanceof HomeFragment) {
                transaction.hide(from).add(R.id.frame_home, to).commit();
            } else {
                transaction.hide(from).add(R.id.frame, to).commit();
            }
        } else {
            transaction.hide(from).show(to).commit();
        }
    }

    private void switchBottom(int position) {
        for (int i = 0; i < 4; i++) {
            if (position == i) {
                bottomText[i].setTextColor(ContextCompat.getColor(this, R.color.color_D83C3D));
            } else {
                bottomText[i].setTextColor(ContextCompat.getColor(this, R.color.black));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                setStatusBarColor(R.color.white, true);
                switchContent(currentFragment, fragments[0]);
                switchBottom(0);
                break;
            case R.id.tv_2:
                setStatusBarColor(R.color.colorAccent, false);
                switchContent(currentFragment, fragments[1]);
                switchBottom(1);
                break;
            case R.id.tv_3:
                setStatusBarColor(R.color.colorPrimary, false);
                switchContent(currentFragment, fragments[2]);
                switchBottom(2);
                break;
            case R.id.tv_4:
                ImmersionBar.with(this).reset().init();
                switchContent(currentFragment, fragments[3]);
                switchBottom(3);
                break;
        }
    }

    private void show() {
        ObjectAnimator showAnimator = ObjectAnimator.ofFloat(findViewById(R.id.frame_home), "translationY", -topSearchHeight, 0);
        showAnimator.setDuration(500);
        showAnimator.start();
    }

    private void hide() {
        ObjectAnimator showAnimator = ObjectAnimator.ofFloat(findViewById(R.id.frame_home), "translationY", 0, -topSearchHeight);
        showAnimator.setDuration(500);
        showAnimator.start();
    }

    private void gestureDetector() {
        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (currentFragment instanceof HomeFragment) {
                    if (e1.getY() - e2.getY() > touchSlop && (Math.abs(e1.getY() - e2.getY()) > Math.abs(e1.getX() - e2.getX()))) {
//                        Log.e("Bill", "上滑");
                        if (topIsShow) {
                            topIsShow = false;
                            hide();
                        }
                        return super.onScroll(e1, e2, distanceX, distanceY);
                    }

                    if (e2.getY() - e1.getY() > touchSlop && (Math.abs(e1.getY() - e2.getY()) > Math.abs(e1.getX() - e2.getX()))) {
//                        Log.e("Bill", "下拉");
                        if (!topIsShow) {
                            topIsShow = true;
                            show();
                        }
                        return super.onScroll(e1, e2, distanceX, distanceY);
                    }
                }

                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mDetector.onTouchEvent(ev)) {
            return mDetector.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
}
