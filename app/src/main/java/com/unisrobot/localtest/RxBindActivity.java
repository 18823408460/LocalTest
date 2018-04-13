package com.unisrobot.localtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.unisrobot.localtest.util.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

import static com.unisrobot.localtest.R.layout.activity_rxbind;

/**
 * Created by Administrator on 2018/4/13.
 */

public class RxBindActivity extends Activity {

        @BindView(R.id.button9)
        Button button;

        private Context context;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(activity_rxbind);
                ButterKnife.bind(this);
                context = this;

                clickTest();
        }

        private void clickTest() {
                RxView.clicks(button).throttleFirst(10, TimeUnit.SECONDS)
                        .subscribe(new Action1<Void>() {
                                @Override
                                public void call(Void aVoid) {
                                        ToastUtil.getToastUtil().show("click");
                                }
                        });
        }

        private void testListViewItem() {
        }
}
