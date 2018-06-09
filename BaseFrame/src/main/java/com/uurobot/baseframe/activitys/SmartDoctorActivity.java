package com.uurobot.baseframe.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.PhoneNumberUtils;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.dialog.SmartDoctorNormalDialog;
import com.uurobot.baseframe.dialog.SmartDoctorUpDialog;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentOne;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentThree;
import com.uurobot.baseframe.fragment.smartdoctor.SmartDoctorFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartDoctorActivity extends BaseFragmentActivity implements View.OnClickListener {
        private List<BaseFragment> baseFragmentList;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_smartdoctor);
                initView();
                initData();

                int i = View.generateViewId();
        }

        private void initData() {
                baseFragmentList = new ArrayList<>();
                baseFragmentList.add(new SmartDoctorFragmentOne());
                baseFragmentList.add(new SmartDoctorFragmentTwo());
                baseFragmentList.add(new SmartDoctorFragmentThree());
                switchFragment(fromFragment, baseFragmentList.get(0));
        }

        private void initView() {
                Button btn_back = findViewById(R.id.btn_head_back);
                Button btn_help = findViewById(R.id.btn_head_help);
                btn_back.setOnClickListener(this);
                btn_help.setOnClickListener(this);
        }

        int index = 0;

        @Override
        public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                        case R.id.btn_head_back:
                                switchFragment(fromFragment, baseFragmentList.get(index++ % baseFragmentList.size()));
                                break;
                        case R.id.btn_head_help:
                                testDialog();
                                break;
                }
        }

        private void testRecyleView() {
                Intent intent = new Intent(this, RecycleActivity.class);
                intent.putExtra(RecycleActivity.TYPE,"TT");
                startActivity(intent);
        }

        private void testDialog() {
                if (this.fromFragment instanceof SmartDoctorFragmentOne) {
                        DialogFragment smartDoctorDialog = SmartDoctorUpDialog.getInstance("检测人体");
                        smartDoctorDialog.show(getSupportFragmentManager(), null);

                } else if (this.fromFragment instanceof SmartDoctorFragmentTwo) {
                        DialogFragment smartDoctorDialog = SmartDoctorNormalDialog.
                                getInstance(" 检测失败，请重新检测!");
                        smartDoctorDialog.show(getSupportFragmentManager(), null);

                } else if (this.fromFragment instanceof SmartDoctorFragmentThree) {
                        DialogFragment smartDoctorDialog = SmartDoctorUpDialog.
                                getInstance("身体棒棒的，成绩\n杠杠的");
                        smartDoctorDialog.show(getSupportFragmentManager(), null);
                }
        }
}
