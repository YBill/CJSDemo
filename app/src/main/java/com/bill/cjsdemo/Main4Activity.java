package com.bill.cjsdemo;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;

/**
 * 这个Activity在布局中添加android:animateLayoutChanges="true"
 * 通过设置LayoutTransition
 * 使头部通过平移动画隐藏，然后下面内容区自然会顶上去，使用简单，但是内容区其实没有平移
 */
public class Main4Activity extends AppCompatActivity {

    private GestureDetector mDetector;
    private View topView;
    private LinearLayout parentView;
    private boolean topIsShow = true;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ImmersionBar
                .with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 1.0f)
                .init();

        height = (int) getResources().getDimension(R.dimen.top_height);
        parentView = findViewById(R.id.parent);
        topView = findViewById(R.id.fl_top);

        findViewById(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
            }
        });

        ObjectAnimator addAnimator = ObjectAnimator.ofFloat(null, "translationY", -height, 0).
                setDuration(1000);
        ObjectAnimator removeAnimator = ObjectAnimator.ofFloat(null, "translationY", 0, -height).
                setDuration(1000);

        LayoutTransition transition = new LayoutTransition();
        transition.setAnimator(LayoutTransition.APPEARING, addAnimator);
        transition.setAnimator(LayoutTransition.DISAPPEARING, removeAnimator);
        parentView.setLayoutTransition(transition);

        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (e1.getY() - e2.getY() > 2 && (Math.abs(e1.getY() - e2.getY()) > Math.abs(e1.getX() - e2.getX()))) {
//                    Log.e("Bill", "上滑");
                    if (topIsShow) {
                        topIsShow = false;
                        topView.setVisibility(View.GONE);
                    }
                    return super.onScroll(e1, e2, distanceX, distanceY);
                }

                if (e2.getY() - e1.getY() > 2 && (Math.abs(e1.getY() - e2.getY()) > Math.abs(e1.getX() - e2.getX()))) {
//                    Log.e("Bill", "下拉");
                    if (!topIsShow) {
                        topIsShow = true;
                        topView.setVisibility(View.VISIBLE);
                    }
                    return super.onScroll(e1, e2, distanceX, distanceY);
                }

                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
    }

    private void initDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.dialog);
        bottomSheetDialog.setContentView(R.layout.layout_sheet_dialog);
//        bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
//                .setBackgroundColor(getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();
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
