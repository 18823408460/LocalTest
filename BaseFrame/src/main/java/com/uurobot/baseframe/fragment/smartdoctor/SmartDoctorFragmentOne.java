package com.uurobot.baseframe.fragment.smartdoctor;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.dialog.SmartDoctorDialog;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorFragmentOne extends BaseFragment {

        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View inflate = inflater.inflate(R.layout.fragmet_smartdoctor_one, container, false);
                return inflate;
        }

        @Override
        public void onResume() {
                super.onResume();


        }
}
