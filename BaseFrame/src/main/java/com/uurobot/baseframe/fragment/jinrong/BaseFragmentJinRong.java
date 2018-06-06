package com.uurobot.baseframe.fragment.jinrong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uurobot.baseframe.R;

/**
 * Created by Administrator on 2018/6/6.
 */

public class BaseFragmentJinRong extends Fragment {
        private static final String TAG = BaseFragmentJinRong.class.getSimpleName();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                LoadingPager loadingPager = new LoadingPager(getContext()) {
                        @Override
                        public int getSuccessViewId() {
                                return R.layout.layout_loading_pager_success;
                        }

                        @Override
                        protected void onSuccess(final String content) {
                                Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
                        }
                };
                return loadingPager;
        }
}
