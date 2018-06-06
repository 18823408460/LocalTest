package com.uurobot.baseframe.fragment.jinrong;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/6/6.
 */

public abstract class LoadingPager extends FrameLayout {
        public enum LoadingState {
                ERROR(0, ""),
                EMPTY(1, ""),
                SUCCESS(2, ""),
                LOADING(3, "");
                private int value;
                private String content;

                LoadingState(int value, String content) {
                        this.value = value;
                        this.content = content;
                }
        }

        private LoadingState loadingState = LoadingState.LOADING;
        private View errroView;
        private View emptyView;
        private View loadingView;
        private View successView;

        public LoadingPager(@NonNull Context context) {
                this(context, null);
        }

        public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
                this(context, attrs, -1);
        }

        public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                initView();
                updatePage();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                loadingState = LoadingState.SUCCESS;
                                loadingState.content = "data Success";
                                updatePage();
                        }
                }, 3000);
        }

        private void initView() {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                errroView = layoutInflater.inflate(R.layout.layout_loading_pager_error, null);
                addView(errroView);
                emptyView = layoutInflater.inflate(R.layout.layout_loading_pager_empty, null);
                addView(emptyView);
                loadingView = layoutInflater.inflate(R.layout.layout_loading_pager_loading, null);
                addView(loadingView);

                successView = layoutInflater.inflate(getSuccessViewId(), null);
                addView(successView);
        }

        public abstract int getSuccessViewId();

        private void updatePage() {
                errroView.setVisibility(GONE);
                emptyView.setVisibility(GONE);
                loadingView.setVisibility(GONE);
                successView.setVisibility(GONE);
                if (loadingState.equals(LoadingState.LOADING)) {
                        loadingView.setVisibility(VISIBLE);
                }
                if (loadingState.equals(LoadingState.EMPTY)) {
                        emptyView.setVisibility(VISIBLE);
                }
                if (loadingState.equals(LoadingState.ERROR)) {
                        errroView.setVisibility(VISIBLE);
                }
                if (loadingState.equals(LoadingState.SUCCESS)) {
                        successView.setVisibility(VISIBLE);
                        onSuccess(loadingState.content);
                }
        }

        protected abstract void onSuccess(String content);
}
