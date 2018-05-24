package com.uurobot.baseframe.fragment.smartdoctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorFragmentTwo extends BaseFragment {


        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.fragmet_smartdoctor_two, container, false);
                return inflate;
        }

        @Override
        public void onResume() {
                super.onResume();


        }
}
