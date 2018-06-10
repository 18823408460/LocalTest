package com.uurobot.baseframe.view.jinrong;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by WEI on 2018/6/10.
 */

/**
 * 侧滑菜单
 */
public class SlideLayout extends FrameLayout {
    private View contentView;
    private View menuView;
    private int conentWidth;
    private int menuWidth;
    private int parentHeight;

    public SlideLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData() {
    }

    private float startX;
    private float startY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //这里不加，有什么影响????
        //super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();

                int distance = (int) (endX - startX); // 这是滑动的距离

                int toScroll = getScrollX() - distance;

                // 这里怎么理解？？？？
                if (toScroll < 0) {
                    toScroll = 0;
                } else if (toScroll > menuWidth) {
                    toScroll = menuWidth;

                }
                // getScrollX ,getScroolY 都是相对没有滑动时默认位置 的  偏移量,,
                scrollTo(toScroll, getScrollY()); // y 始终没有滑动，所以 getScrollY() 始终=0

                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        conentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
        parentHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuView.layout(conentWidth, 0, menuWidth + conentWidth, parentHeight);
    }

    // 如果是滑动，进行拦截，防止 孩子 view 消耗这个事件
    private float downX;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean interrupt = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float abs = Math.abs(endX - downX);
                if (abs > 8) {
                    interrupt = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return interrupt;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);

        // SlideLayout 的 子 view 设置点击监听，意味着消耗事件，这样 SlideLayout就收不到向上传递过来的事件了..
        contentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "view= content", Toast.LENGTH_SHORT).show();
            }
        });
        menuView = getChildAt(1);
        menuView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "view= menu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
