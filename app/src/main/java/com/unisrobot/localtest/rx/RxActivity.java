package com.unisrobot.localtest.rx;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.unisrobot.localtest.R;
import com.unisrobot.localtest.robot.Sensor.SensorManager;

import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/11.
 */

/**
 * 采用订阅（Rxjava， EventBus），就可以避免使用太多的 接口，监听器 等处理回调信息。。。
 */
public class RxActivity extends Activity {

        private static final String TAG = "RxActivity";

        @BindView(R.id.button7)
        Button button7;

        @BindView(R.id.button8)
        Button button8;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_rx);
                ButterKnife.bind(this);
                SensorManager.getSensorManager();
        }

        @OnClick(R.id.button7)
        public void test() {
//                RxMgr.getOkHttpMgr().test3();
                Rx2Mgr.getOkHttpMgr().testSchedule();
//                textConn();
        }

        private void alibaba(){
//                SecureRandom
//                IvParameterSpec
        }


        @OnClick(R.id.button8)
        public void dis() {
                // ConnectableFlowable 同一个个时刻只能有一个 订阅者
                if (subscribe != null) {
                        boolean disposed = subscribe.isDisposed();
                        if (!disposed) {
                                subscribe.dispose();
                        }
                }
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                Log.e(TAG, "accept run: 2220-000000000");
                                subscribe = publish.subscribe(new Consumer<String>() {
                                        @Override
                                        public void accept(String s) throws Exception {
                                                Log.e(TAG, "222accept: " + s);
                                                SystemClock.sleep(2500);
                                        }
                                });
                        }
                }, 3000);
        }


        int countData = 1;

        Disposable subscribe;

        ConnectableFlowable<String> publish;

        private void textConn() {
                publish = Flowable.create(new FlowableOnSubscribe<String>() {
                        @Override
                        public void subscribe(FlowableEmitter<String> e) throws Exception {
                                while (true) {
                                        e.onNext("sub=" + (countData++));
                                        Log.e(TAG, "subscribe: " + countData);
                                        SystemClock.sleep(1000);
                                }
                        }
                }, BackpressureStrategy.LATEST).subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .doOnCancel(new Action() {
                                @Override
                                public void run() throws Exception {
                                        Log.e(TAG, "run: -----canle run");
                                }
                        })
                        .publish();
                publish.connect();
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                subscribe = publish.subscribe(new Consumer<String>() {
                                        @Override
                                        public void accept(String s) throws Exception {
                                                Log.e(TAG, "111accept: " + s);
                                                SystemClock.sleep(2000);
                                        }
                                });
                        }
                }, 4000);
        }


        private void init() {
                Log.e(TAG, "testZip: ");
                Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                int a = 100;
                                for (int i = 0; i < 4; i++) {
                                        e.onNext(a--);
                                        Log.e(TAG, "subscribe: observable" + "   " + Thread.currentThread().getName());
                                }
                        }
                });

                Observable<Integer> observable1 = observable.subscribeOn(Schedulers.io());

                Observable<Integer> sorted = observable.sorted();


                Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                int a = 50;
                                for (int i = 0; i < 4; i++) {
                                        Log.e(TAG, "subscribe: observable2===" + i + "   " + Thread.currentThread().getName());
                                        e.onNext(a--);
                                }
                        }
                }).subscribeOn(Schedulers.io());

                Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, String>() {
                        @Override
                        public String apply(Integer integer, Integer integer2) throws Exception {
                                Log.e(TAG, "apply: " + integer + "   " + integer2 + "   " + Thread.currentThread().getName());
                                return "zip=" + integer + ":" + integer2;
                        }
                }).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept: " + s + "     " + Thread.currentThread().getName());
                        }
                });
        }

}
