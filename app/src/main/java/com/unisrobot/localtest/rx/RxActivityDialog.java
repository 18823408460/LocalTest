package com.unisrobot.localtest.rx;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.unisrobot.localtest.R;
import com.unisrobot.localtest.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/13.
 */

public class RxActivityDialog extends Activity {

        private static final String TAG = "RxActivityDialog";

        @BindView(R.id.button10)
        Button button10;

        @BindView(R.id.button11)
        Button button11;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_rxdialog);
                ButterKnife.bind(this);
        }

        Disposable disposable ;
        @OnClick(R.id.button11)
        public void cancel(){
                if (disposable != null && !disposable.isDisposed()){
                        disposable.dispose();
                        Log.e(TAG, "cancel: " );
                }
        }

        @OnClick(R.id.button10)
        public void testDialog() {
                Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                SystemClock.sleep(10000);
                                e.onNext("dd");
                                SystemClock.sleep(10000);
                                e.onComplete();
                        }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

                Observer<String> observer = new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                disposable = d ;
                                Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
                                ToastUtil.getToastUtil().show("start");
                        }

                        @Override
                        public void onNext(String s) {
                                Log.e(TAG, "onNext: " + Thread.currentThread().getName());
                                ToastUtil.getToastUtil().show("onNext");
                        }

                        @Override
                        public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + Thread.currentThread().getName());
                                ToastUtil.getToastUtil().show("onError");
                        }

                        @Override
                        public void onComplete() {
                                Log.e(TAG, "onComplete: " + Thread.currentThread().getName());
                                ToastUtil.getToastUtil().show("onComplete");
                        }
                };
                observable.subscribe(observer);
        }
}
