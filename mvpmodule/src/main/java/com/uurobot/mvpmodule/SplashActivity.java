package com.uurobot.mvpmodule;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.uurobot.mvpmodule.base.BaseActivity;
import com.uurobot.mvpmodule.base.IBaseContract;
import com.uurobot.mvpmodule.widget.SimpleButton;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/14.
 */

public class SplashActivity extends BaseActivity {
        private static final String TAG = SplashActivity.class.getSimpleName();

        @BindView(R.id.sb_skip)
        SimpleButton sbSkip;
        private CountDownTimer countDownTimer;

        @Override
        protected void initView() {

        }

        @Override
        protected void initData() {
                sbSkip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                cancelCountDown();
                                toHomeActivity();
                        }
                });
                initCountDown();
        }

        private void toHomeActivity() {
                gotoActivity(HomeActivity.class);
                finish();
        }

        private void cancelCountDown() {
                if (countDownTimer != null ){
                        countDownTimer.cancel();
                }
        }

        private void initCountDown() {
                countDownTimer = new CountDownTimer(5000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) { //这个计数不是很准确
                                Log.d(TAG, "onTick: " + millisUntilFinished/1000+ "   ");
                                sbSkip.setText("跳过 " + millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                                Log.e(TAG, "onFinish: ");
                                toHomeActivity();
                        }
                };
                countDownTimer.start();
        }

        @Override
        protected int getViewLayoutId() {
                return R.layout.activity_splash;
        }

        @Override
        protected IBaseContract.IBasePresenter getPreSenter() {
                return null;
        }

}
