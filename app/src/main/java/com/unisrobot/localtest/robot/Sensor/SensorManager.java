package com.unisrobot.localtest.robot.Sensor;

import android.util.Log;

import com.unisrobot.localtest.threadPool.ThreadPoolMgr;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/11.
 */

public class SensorManager {

        private static final String TAG = "SensorManager";
        private static volatile SensorManager sensorManager;

        private Observable<SensorEvent> sensorEventObservable;

        private Subscription subscription;

        public static SensorManager getSensorManager() {
                if (sensorManager == null) {
                        synchronized (SensorManager.class) {
                                if (sensorManager == null) {
                                        sensorManager = new SensorManager();
                                }
                        }
                }
                return sensorManager;
        }

        private int testData = 0;

        private SensorManager() {
                ConnectableObservable connectableObservable = getObservable().subscribeOn(Schedulers.io()).publish();
                connectableObservable.connect();
                sensorEventObservable = connectableObservable ;
                ThreadPoolMgr.getInstance().excute(new Runnable() {
                        @Override
                        public void run() {
                                while (true) {
                                        testData = testData + 1;
                                        try {
                                                Thread.sleep(3000);
                                        }
                                        catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                });
        }

        private Observable<SensorEvent> getObservable() {
                return Observable.create(new ObservableOnSubscribe<SensorEvent>() {
                        @Override
                        public void subscribe(ObservableEmitter<SensorEvent> e) throws Exception {
                                // 这里是在子线程。。。。。
                                if (testData%2==0){
                                        Log.e(TAG, "subscribe: " );
                                }else {
                                        Log.e(TAG, "subscribe: 1111");
                                }
                        }
                });
        }
}
