package com.uurobot.mvpmodule.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.uurobot.mvpmodule.R;
import com.uurobot.mvpmodule.dragger.Tester;
import com.uurobot.mvpmodule.utils.SwipeRefreshHelper;
import com.uurobot.mvpmodule.widget.EmptyLayout;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

/**
 * SwipeRefreshLayout只能有一个孩子，当然我们不般也不会往里面放其他的布局。我们只需要在容器里包裹一个ListView就好了
 */
public class BaseLoadingActivity extends AppCompatActivity implements EmptyLayout.OnRetryListener {
        private static final String TAG = BaseLoadingActivity.class.getSimpleName();
        @BindView(R.id.rv_news_list)
        RecyclerView rvNewsList;
        @BindView(R.id.empty_layout)
        EmptyLayout mEmptyLayout;
        @BindView(R.id.swipe_refresh)
        SwipeRefreshLayout mSwipeRefresh;

        @Inject
        Tester tester ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                AndroidInjection.inject(this);
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_base_loading);
                ButterKnife.bind(this);
                initSwipeRefresh();
                initTimer();

                tester.printHello();
        }

        private int count = 0;

        private void initTimer() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                                count++;
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                handlerState();
                                        }
                                });
                        }
                };
                timer.schedule(timerTask, 3000, 3000);
        }

        private void handlerState() {
                if (count % 5 == 0) {
                        showLoading();
                } else if (count % 5 == 1) {
                        showNetError();
                } else if (count % 5 == 2) {
                        showNoData();
                } else if (count % 5 == 3) {
                        showNoNet();
                } else if (count % 5 == 4) {
                        hideLoading();
                }
        }

        private void finishRefresh() {
                if (mSwipeRefresh != null) {
                        mSwipeRefresh.setRefreshing(false);
                }
        }

        /**
         * 初始化下拉刷新
         */
        private void initSwipeRefresh() {
                if (mSwipeRefresh != null) {
                        SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                        Log.e(TAG, "onRefresh: " );
                                        new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                        finishRefresh();
                                                }
                                        },2000);
                                }
                        });
                }
        }

        private void updateViews(boolean b) {
                Log.e(TAG, "updateViews: " + b);
        }

        private void showLoading() {
                if (mEmptyLayout != null) {
                        mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
                }
        }

        private void showNoData() {
                if (mEmptyLayout != null) {
                        mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
                }
        }

        private void showNoNet() {
                if (mEmptyLayout != null) {
                        mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
                }
        }

        private void hideLoading() {
                if (mEmptyLayout != null) {
                        mEmptyLayout.hide();
                }
        }

        private void showNetError() {
                if (mEmptyLayout != null) {
                        mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
                        mEmptyLayout.setRetryListener(this);
                }
        }


        @Override
        public void onRetry() {
                updateViews(false);
        }
}
