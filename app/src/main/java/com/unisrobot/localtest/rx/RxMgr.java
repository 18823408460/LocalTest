package com.unisrobot.localtest.rx;


/**
 * Created by Administrator on 2018/4/11.
 * * io.reactivex » rxjava
 * <p>
 * <p>
 * 1.0.0 --- 1.3.8
 */

/**
 * Observable(被观察者-事件源)----------------Observer(观察者)
 */
public class RxMgr {

//        private static final String TAG = "RxMgr";
//
//        private static RxMgr rxMgr;
//
//        public static RxMgr getOkHttpMgr() {
//                if (rxMgr == null) {
//                        synchronized (RxMgr.class) {
//                                if (rxMgr == null) {
//                                        rxMgr = new RxMgr();
//                                }
//                        }
//                }
//                return rxMgr;
//        }
//
//        public void test1() {
//                // 创建被观察者--- 产生数据，，，
//                Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//                        @Override
//                        public void call(Subscriber<? super String> subscriber) {
//                                subscriber.onNext("hello first");
//                        }
//                });
//                Subscriber<String> stringSubscriber = new Subscriber<String>() {
//                        @Override
//                        public void onCompleted() {
//                                Log.e(TAG, "onCompleted: ");
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                                Log.e(TAG, "onError: ");
//                        }
//
//                        @Override
//                        public void onNext(String s) {
//                                Log.e(TAG, "onNext: " + s);
//                        }
//                };
//                observable.subscribe(stringSubscriber);
//        }
//
//
//        // 线程切换
//        public void test2() {
//                // 创建被观察者--- 产生数据，，，
//                Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//                        @Override
//                        public void call(Subscriber<? super String> subscriber) {
//                                Log.e(TAG, "call: " + Thread.currentThread().getName());
//                                subscriber.onNext("hello first");
//
//                        }
//                });
//                Subscriber<String> stringSubscriber = new Subscriber<String>() {
//                        @Override
//                        public void onCompleted() {
//                                Log.e(TAG, "onCompleted: ");
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                                Log.e(TAG, "onError: ");
//                        }
//
//                        @Override
//                        public void onNext(String s) {
//                                Log.e(TAG, "onNext: " + s);
//                                Log.e(TAG, "onNext: " + Thread.currentThread().getName());
//                        }
//                };
//                observable.subscribeOn(Schedulers.io()) // 这里怎么理解？？？？
//                        .observeOn(AndroidSchedulers.mainThread())//
//                        .subscribe(stringSubscriber);
//        }
//
//        // rxjava 产生数据源的方式：(各种操作符的使用, 下面都是--偷懒方式)
//
//        /**
//         * 1. 创建型
//         * Just()：上面介绍过，一次发送
//         * from()：  将一个Iterable, 一个Future, 或者一个数组转换成一个Observable，例子中也有
//         * repeat():创建一个重复发射指定数据或数据序列的Observable。
//         * defer()：只有当订阅者订阅才创建Observable；为每个订阅创建一个新的Observable。
//         * range():创建一个发射指定范围的整数序列的Observable。 如range（10，5），则为依次发送10，11，12，13，14，15
//         * interval()：Observable.interval(5, TimeUnit.SECONDS)创建一个按照给定的时间间隔发射整数序列的Observable
//         * timer()：Observable.timer(5, TimeUnit.SECONDS) 延时5秒发送
//         * empty()--创建一个什么都不做直接通知完成的Observable
//         * error()--创建一个什么都不做直接通知错误的Observable
//         * never()--创建一个不发射任何数据的Observable
//         * 2. 过滤型
//         * filter():
//         * take( )
//         * takeLast()
//         * distinct( )过滤掉重复数据
//         * distinctUntilChanged()过滤掉连续重复的数据。
//         * first()只发射第一项数据
//         * last()只发射最后的一项数据。
//         * skip( ) 跳过开始的N项数据
//         * skipLast( ) — 跳过最后的N项数据。
//         * elementAt( ) 发射第N项数据
//         * elementAtOrDefault( )发射第N项数据，如果Observable数据少于N项就发射默认值。
//         * sample( )
//         * throttleLast( )
//         * throttleFirst( )定期发射Observable发射的第一项数据
//         * timeout( )如果在一个指定的时间段后还没发射数据，就发射一个异常
//         * ……
//         * ……
//         * 3. 变换型
//         * map()
//         * FlatMap（）
//         * scan()具有累加器的功能，可以将前一个的值传递给后面使用。
//         * buffer( )它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个。
//         * 4. 其他类型
//         */
//        public void test3() {
//                Observable.just(1, 2, 3).subscribe(new Action1<Integer>() {
//                        @Override
//                        public void call(Integer integer) {
//                                Log.e(TAG, "just call: " + integer);
//                        }
//                });
//
//                String[] strings = {"1", "2"};
//                Observable.from(strings).subscribe(new Action1<String>() {
//                        @Override
//                        public void call(String s) {
//                                Log.e(TAG, "from call: " + s);
//                        }
//                });
//
//                //  Func1<转换前T，转换后S>，就是传入T，返回S
//                Observable.just(1, 2).map(new Func1<Integer, String>() {
//                        @Override
//                        public String call(Integer integer) {
//                                return "fun+" + integer;
//                        }
//                }).subscribe(new Action1<String>() {
//                        @Override
//                        public void call(String s) {
//                                Log.e(TAG, "fun call: " + s + "  thread==" + Thread.currentThread().getName());
//                        }
//                });
//
//                Observable.just(1, 2).flatMap(new Func1<Integer, Observable<String>>() {
//                        @Override
//                        public Observable<String> call(Integer integer) { // flapmap返回的是一个Observable
//                                return Observable.just("flapMap=" + integer);
//                        }
//                }).subscribe(new Action1<String>() {
//                        @Override
//                        public void call(String s) {
//                                Log.e(TAG, "flapMap call: " + s);
//                        }
//                });
//        }
}
