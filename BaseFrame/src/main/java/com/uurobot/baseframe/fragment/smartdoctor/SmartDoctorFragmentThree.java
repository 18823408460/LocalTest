package com.uurobot.baseframe.fragment.smartdoctor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.view.AnimImageView;
import com.uurobot.baseframe.view.SurfaceViewAnim;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorFragmentThree extends BaseFragment {
        private static final String TAG = SmartDoctorFragmentThree.class.getSimpleName();
        private AnimImageView animImageView;

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.fragmet_smartdoctor_three, null);
                animImageView = inflate.findViewById(R.id.surfaceview_smartdoctor_three);
                animImageView.startAnim();
                return inflate;
        }

        @Override
        public void onPause() {
                super.onPause();
                Log.d(TAG, "onPause: ");
        }

        @Override
        public void onResume() {
                super.onResume();
                Log.d(TAG, "onResume: ");
        }

        @Override
        public void onHiddenChanged(boolean hidden) {
                super.onHiddenChanged(hidden);
                Log.e(TAG, "onHiddenChanged: "+hidden );
                if (hidden){
                        animImageView.stopAnim();
                }else {
                        animImageView.startAnim();
                }
        }
}
