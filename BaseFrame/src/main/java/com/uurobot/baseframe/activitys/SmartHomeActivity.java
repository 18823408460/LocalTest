package com.uurobot.baseframe.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.base.BaseFragment;
import com.uurobot.baseframe.fragment.smarthome.SmartHomeFragmentOne;
import com.uurobot.baseframe.fragment.smarthome.SmartHomeFragmentThree;
import com.uurobot.baseframe.fragment.smarthome.SmartHomeFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SmartHomeActivity extends BaseFragmentActivity {
        private List<BaseFragment> baseFragmentList;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_smarthome);
                initData();
        }

        private void initData() {
                baseFragmentList = new ArrayList<>();
                baseFragmentList.add(new SmartHomeFragmentOne());
                baseFragmentList.add(new SmartHomeFragmentTwo());
                baseFragmentList.add(new SmartHomeFragmentThree());
                switchFragment(fromFragment, baseFragmentList.get(2));
        }

        int index = 0;

        public void switchF(View view) {
                switchFragment(fromFragment, baseFragmentList.get(index++ % baseFragmentList.size()));
        }
}
