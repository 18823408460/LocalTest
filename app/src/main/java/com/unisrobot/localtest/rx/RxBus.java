package com.unisrobot.localtest.rx;


import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by WEI on 2018/4/16.
 */

/**
 * SerializedSubject ???
 * PublishSubject ??
 */
public class RxBus {

        private static volatile RxBus defaultInstance;

        private Subject<Object, Object> bus;

        private RxBus() {
//                bus = PublishSubject.create();
                bus = new SerializedSubject<>(PublishSubject.create());
        }

        // 单例RxBus
        public static RxBus getDefault() {
                if (defaultInstance == null) {
                        synchronized (RxBus.class) {
                                if (defaultInstance == null) {
                                        defaultInstance = new RxBus();
                                }
                        }
                }
                return defaultInstance;
        }


        // 发送一个新的事件, 下面这个方法解决不了 并发问题。。
//        public synchronized void post(Object o) {
//                bus.onNext(o);
//        }
//
        public  void post(Object o) {
                bus.onNext(o);
        }

        // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
        public <T> Observable<T> toObservable(Class<T> eventType) {
                return bus.ofType(eventType);
//        这里感谢小鄧子的提醒: ofType = filter + cast
//        return bus.filter(new Func1<Object, Boolean>() {
//            @Override
//            public Boolean call(Object o) {
//                return eventType.isInstance(o);
//            }
//        }) .cast(eventType);
        }
}
