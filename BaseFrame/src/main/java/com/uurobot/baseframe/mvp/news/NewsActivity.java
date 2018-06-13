package com.uurobot.baseframe.mvp.news;

import android.util.Log;

import com.uurobot.baseframe.mvp.base.BaseActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/6/13.
 */

/**
 * 后面的泛型如果不写，则默认是IPresenter
 *
 *
 * 数据全部 由Presenter去获取，在Presenter中包含IView，然后去调用里面的方法回传到Activity中刷新界面
 * 意味了：
 *
 *     》该类中所有界面的更新，都要写到对应的IView中，
 *     》该类中所有数据的获取。都要写到对应的Presenter中。。
 *
 *
 */
public class NewsActivity extends BaseActivity<NewPresenter> implements INewsContract.IView {
        private static final String TAG = NewsActivity.class.getSimpleName();

        @Override
        protected NewPresenter getPresenter() {
                return new NewPresenter();
        }

        @Override
        protected void initData() {
                //获取数据
                mPresenter.getNews("");
        }

        @Override
        protected void initView() {

        }

        @Override
        protected int getLayoutId() {
                return 0;
        }

        @Override
        public void updateNews(List<String> data) {
                Log.e(TAG, "updateNews: ======== " + data);
        }
}
