package com.unisrobot.localtest.rx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;


import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/11.
 * <p>
 * <p>
 * <p>
 * io.reactivex.rxjava2 » rxjava
 * <p>
 * 2.0.0 --- 2.1.12
 */

/**
 * 2. 新增Flowable
 * <p>
 * RxJava1 中 Observable 不能很好地支持 backpressure ，会抛出MissingBackpressureException。
 * 所以在 RxJava2 中 Oberservable 不再支持 backpressure ，而使用新增的 Flowable 来支持 backpressure
 * <p>
 * <p>
 * <p>RxJava2.X中，Observeable用于订阅Observer，是不支持背压的，而Flowable用于订阅Subscriber，是支持背压(Backpressure)的
 * <p>
 * <p>
 * 3. ActionN 和 FuncN 改名
 * 其中，Action0 改名成Action，Action1改名成Consumer，而Action2改名成了BiConsumer，而Action3 - Action9都不再使用了，ActionN变成了Consumer<Object[]> 。
 * 同样，Func改名成Function，Func2改名成BiFunction，Func3 - Func9 改名成 Function3 - Function9，FuncN 由 Function<Object[], R> 取代
 * <p>
 * 4.
 * <p>上游可以发送无限个onNext, 下游也可以接收无限个onNext.当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送,
 * 而下游收到onComplete事件之后将不再继续接收事件.当上游发送了一个onError后,  上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
 * <p>
 * 5. 比如发送多个onComplete是可以正常运行的, 依然是收到第一个onComplete就不再接收了, 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃
 * <p>
 * <p>
 * <p>接下来介绍Disposable, 这个单词的字面意思是一次性用品,用完即可丢弃的.  那么在RxJava中怎么去理解它呢, 对应于上面的水管的例子,
 * 我们可以把它理解成两根管道之间的一个机关, 当调用它的dispose()方法时,
 * 它就会将两根管道切断, 从而导致下游收不到事件.注意: 调用dispose()并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.
 * <p>
 * <p>
 * <p>
 * 6. Rxjava的性能更加好。。。
 */
public class Rx2Mgr {

        private static final String TAG = "RxMgr";

        private volatile static Rx2Mgr rxMgr;

        public static Rx2Mgr getOkHttpMgr() {
                if (rxMgr == null) {
                        synchronized (RxMgr.class) {
                                if (rxMgr == null) {
                                        rxMgr = new Rx2Mgr();
                                }
                        }
                }
                return rxMgr;
        }

        public void test1() {
                // Observable.OnSubscribe 变成 ObservableOnSubscribe,Observable.OnSubscribe 变成 ObservableOnSubscribe
                //ObservableEmitter 可以理解为发射器，是用来发出事件的，它可以发出三种类型的事件，通过调用emitter的onNext(T value) 、
                // onComplete()和onError(Throwable error)可以分别发出next事件、complete事件和error事件。 如果只关心next事件的话，只需单独使用onNext()即可
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("hello rxjav2");
                        }
                }).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept: " + s);
                        }
                });
        }

        public void test2() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("hello");
                                e.onError(new Throwable("ssss"));
                        }
                }).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept: " + s);
                        }
                }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "Throwable accept: " + throwable);
                        }
                });
        }

        public void test33() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("111");
                                e.onNext("11123333");
                                e.onError(new Throwable("error")); // onError / onComplete执行完毕后，后面的流程不在执行。
                                e.onNext("sdfds");
                                e.onComplete();
                        }
                }).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                Log.e(TAG, "onSubscribe: ");
                        }

                        @Override
                        public void onNext(String s) {
                                Log.e(TAG, "onNext: " + s);
                        }

                        @Override
                        public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e);
                        }

                        @Override
                        public void onComplete() {
                                Log.e(TAG, "onComplete: ");
                        }
                });
        }

        public void test44() {
                // Func改名成Function，Func2改名成BiFunction，Func3 - Func9 改名成 Function3 - Function9，FuncN 由 Function<Object[], R> 取代
                Observable.just(1, 23)
                        .map(new Function<Integer, String>() {

                                @Override
                                public String apply(Integer integer) throws Exception {
                                        return "map+" + integer;
                                }
                        }).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept: " + s);
                        }
                }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "Throwable accept: " + throwable);
                        }
                });
        }

        public void testOnErrorReturn() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                Log.e(TAG, "subscribe: ");
                                e.onNext("1111");
                                e.onNext("2222");
                                e.onError(new Throwable("error")); // 一旦出错，下面的不再执行，但是这个Throwable，用下面的替换
                                e.onNext("4444");
                        }
                }).onErrorReturn(new Function<Throwable, String>() {
                        @Override
                        public String apply(Throwable throwable) throws Exception {
                                return "3333";
                        }
                }).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept: " + s);
                        }
                });
        }

        public void testOnErrorResumeNext() {
//                Observable.create(new ObservableOnSubscribe<String>() {
//                        @Override
//                        public void subscribe(ObservableEmitter<String> e) throws Exception {
//                                Log.e(TAG, "subscribe: ");
//                                e.onNext("1111");
//                                e.onNext("2222");
//                                e.onError(new Throwable("error")); // 一旦出错，下面的不再执行，但是这个Throwable，用下面的替换,下面的obs会继续发射数据
//                                e.onNext("4444");
//                        }
//                }).onErrorResumeNext(new Observable<String>() {
//                        @Override
//                        protected void subscribeActual(Observer<? super String> observer) {
//                                //observer.onNext("3333");
//                        }
//                }).subscribe(new Consumer<String>() {
//                        @Override
//                        public void accept(String s) throws Exception {
//                                Log.e(TAG, "accept: " + s);
//                        }
//                });
//                Observable.create(new ObservableOnSubscribe<String>() {
//                        @Override
//                        public void subscribe(ObservableEmitter<String> e) throws Exception {
//                                Log.e(TAG, "subscribe: ");
//                                e.onNext("1111");
//                                e.onNext("2222");
//                                e.onError(new Throwable("error")); // 一旦出错，下面的不再执行，但是这个Throwable，用下面的替换
//                                e.onNext("4444");
//                        }
//                }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
//                        @Override
//                        public ObservableSource<? extends String> apply(Throwable throwable) throws Exception {
//                                Log.e(TAG, "apply: "+throwable.toString() );
//                                return Observable.just("333");
//                        }
//                }).subscribe(new Consumer<String>() {
//                        @Override
//                        public void accept(String s) throws Exception {
//                                Log.e(TAG, "accept: " + s);
//                        }
//                });

                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                Log.e(TAG, "subscribe: ");
                                e.onNext("1111");
                                e.onNext("2222");
                                e.onError(new Throwable("error")); // 一旦出错，下面的不再执行，但是这个Throwable，用下面的替换
                                e.onNext("4444");
                        }
                }).onErrorReturnItem("33333333")
                        .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "accept: " + s);
                                }
                        });
        }

        private boolean error = true;

        public void testRetry1() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("1111");
                                e.onNext("2222");
                                if (error) {
//                                        error = false;
                                        e.onError(new Throwable("333")); //一旦出错，将会重新订阅
                                }
                                e.onNext("4444");
                        }
                }).retry(3).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                Log.e(TAG, "onSubscribe: ");
                        }

                        @Override
                        public void onNext(String s) {
                                Log.e(TAG, "onNext: " + s);
                        }

                        @Override
                        public void onError(Throwable e) { // 超过重试次数，才会回调这里
                                Log.e(TAG, "onError: " + e);
                        }

                        @Override
                        public void onComplete() {
                                Log.e(TAG, "onComplete: ");
                        }
                });
        }


        public void test3() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("111");
                                e.onNext("11123333");
                                e.onError(new Throwable("error")); // onError / onComplete执行完毕后，后面的流程不在执行。
                                e.onNext("sdfds");
                                e.onComplete();
                        }
                }).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                Log.e(TAG, "onSubscribe: ");
                        }

                        @Override
                        public void onNext(String s) {
                                Log.e(TAG, "onNext: " + s);
                        }

                        @Override
                        public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e);
                        }

                        @Override
                        public void onComplete() {
                                Log.e(TAG, "onComplete: ");
                        }
                });
        }


        /**
         * retry
         */
        public void testRetry() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                Log.e(TAG, "subscribe: ");
                                e.onNext("hello");
                        }
                }).repeat(1)
                        .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "accept: " + s);
                                }
                        });
        }

        public void testRepeatWhen1() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("hello");
                                e.onError(new Throwable("error"));
                                e.onComplete();
                        }
                }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                                Log.e(TAG, "apply: ");
                                SystemClock.sleep(2000);
                                return Observable.just("111", "222"); //这里发送的数据没用？？？？？
//                                return null;
                        }
                }).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                Log.e(TAG, "onSubscribe: ");
                        }

                        @Override
                        public void onNext(String s) {
                                Log.e(TAG, "onNext: " + s);
                        }

                        @Override
                        public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e);
                        }

                        @Override
                        public void onComplete() {
                                Log.e(TAG, "onComplete: ");
                        }
                });
        }

        public void repeatWhen2() {
                Observable.range(1, 4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                                Log.e(TAG, "apply: obj====" + objectObservable);
                                return Observable.interval(3, TimeUnit.SECONDS);
                        }
                }).subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {
                                Log.e(TAG, "accept: " + integer);
                        }
                });
        }


        public void test4() {
                // Func改名成Function，Func2改名成BiFunction，Func3 - Func9 改名成 Function3 - Function9，FuncN 由 Function<Object[], R> 取代
//                Observable.just(1, 23)
//                        .map(new Function<Integer, String>() {
//
//                                @Override
//                                public void accept(String s) throws Exception {
//                                        Log.e(TAG, "accept: " + s);
//                                }
//                        });

                Observable.just("hello", "world")
                        .flatMap(new Function<String, ObservableSource<String>>() {
                                @Override
                                public ObservableSource<String> apply(String s) throws Exception {
                                        return Observable.just("flapMap===" + s);
                                }
                        }).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept: " + s);
                        }
                });
        }

        // merge时的数据时串行执行还是并行执行，要看两个oberverable是否在同一个线程
        public void testMerge() {
                Log.e(TAG, "testMerge: ");
                Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(final ObservableEmitter<String> e) throws Exception {
                                for (int i = 0; i < 3; i++) {
                                        e.onNext("observable11");
                                        Log.e(TAG, "subscribe11: " + Thread.currentThread().getName());

                                }
                        }
                });
                Observable<String> observable1 = observable.subscribeOn(Schedulers.newThread());
                Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(final ObservableEmitter<String> e) throws Exception {
                                for (int i = 0; i < 3; i++) {
                                        e.onNext("observable22");
                                        Log.e(TAG, "subscribe22: " + Thread.currentThread().getName());
                                        SystemClock.sleep(1);
                                }
                        }
                });
                observable2.subscribeOn(Schedulers.newThread());
                Observable.merge(observable2, observable)
                        //.subscribeOn(Schedulers.computation()) //io=RxCachedThreadScheduler-1  newThread=RxNewThreadScheduler  computation=RxComputationThreadPool
                        .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "accept: " + s);
                                }
                        });
        }

        public void testMerge2() {
                Observable.merge(Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                try {
                                        Log.e(TAG, "start 1");
                                        int i = 50;
                                        while (i > 0) {
                                                e.onNext(i + " ==== first=" + Thread.currentThread().getName());
                                                Thread.sleep(2);
                                                i--;
                                        }
                                        Log.e(TAG, "onCompleted");
                                        e.onComplete();

                                }
                                catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                }
                        }
                }).subscribeOn(Schedulers.newThread()), Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                try {
                                        Log.e(TAG, "start 2");
                                        int i = 50;
                                        while (i > 0) {
                                                e.onNext(i + "  ===second-" + Thread.currentThread().getName());
                                                Thread.sleep(1);
                                                i--;
                                        }
                                        Log.e(TAG, "onCompleted");
                                        e.onComplete();

                                }
                                catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                }
                        }
                }).subscribeOn(Schedulers.newThread())).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "accept " + s + "     " + Thread.currentThread().getName());
                        }
                });
        }

        //                        .subscribeOn(Schedulers.io())//这句话加不加，对 subscribe，apply 有很大影响(这句是说让所有的 Observable运行在io()线程，所以
        //多个 Observable可能就在同一个io中执行了，，他会覆盖Observable自己设置的属性)
//                        .observeOn(Schedulers.io()) //这句话加不加，对 accept有很大的影响，
        public void testZip11() {
                Log.e(TAG, "testZip: ");
                final Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
                        @Override
                        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                                int a = 100;
                                for (int i = 0; i < 4; i++) {
                                        e.onNext(a--);
                                        Log.e(TAG, "subscribe: observable" + "   " + Thread.currentThread().getName());
                                }
                        }
                });

                // 这种写法和 上面链式调用会导致 subscribe（）执行在不同的线程？？？
                observable.subscribeOn(Schedulers.io());
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

                Observable.zip(observable, observable2, new BiFunction<Integer, Integer, String>() {
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


        /**
         * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
         * <p>
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
         * <p>
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次
         */
        public void testSchedule11() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                        }
                }).subscribeOn(Schedulers.io())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "1accept: " + Thread.currentThread().getName());
                                }
                        })
                        .observeOn(Schedulers.io()).doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "2accept: " + Thread.currentThread().getName());
                        }
                })
                        .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "3accept: " + Thread.currentThread().getName());
                                }
                        });
        }

        /**
         * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
         Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
         Schedulers.newThread() 代表一个常规的新线程
         AndroidSchedulers.mainThread() 代表Android的主线程
         Schedulers.immediate()直接在当前线程运行，相当于不指定线程，默认的Scheduler
         */
        /**
         * observeOn() 指定的是它之后的操作所在的线程。因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可。
         * subscribeOn() 的位置放在哪里都可以，但它是只能调用一次的。
         * <p>
         * Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
         * .subscribeOn(Schedulers.io())
         * .observeOn(Schedulers.newThread())
         * .map(mapOperator) // 新线程，由 observeOn() 指定
         * .observeOn(Schedulers.io())
         * .map(mapOperator2) // IO 线程，由 observeOn() 指定
         * .observeOn(AndroidSchedulers.mainThread)
         * .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
         * <p>
         * observeOn()是用来指定下游observer回调发生的线程
         */


        /**
         * RxJava还有一个API能达到类似的效果，就是from()，但是因为在使用java8编译时，javac不能够区分功能接口类型，所以它在2.x中被拆分为：fromArray,fromIterable,fromFuture
         * <p>
         * from() -- 只是将数组的元素一个一个单独发送出去。
         * <p>
         * flatMap和map还是有共同点的，都是将一个对象转换为另一个对象，不同的是map只是一对一的转换，而flatMap可以是一对多的转换，并且是转换为另外一个Flowable对象
         */
        public void text4() {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
                if (file == null) {
                        return;
                }
                Observable.fromArray(file)
                        .flatMap(new Function<File, ObservableSource<File>>() {
                                @Override
                                public ObservableSource<File> apply(File file) throws
                                        Exception {
                                        return Observable.fromArray(file.listFiles());
                                }
                        }).filter(new Predicate<File>() {
                        @Override
                        public boolean test(File file) throws Exception {
                                return file.getName().endsWith("*.png");
                        }
                }).map(new Function<File, Bitmap>() {
                        @Override
                        public Bitmap apply(File file) throws Exception {
                                return getBitmap(file);
                        }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Bitmap>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Bitmap bitmap) {
//                        imgeView.set
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                        });
        }

        private Bitmap getBitmap(File file) {
                return BitmapFactory.decodeFile(file.getAbsolutePath());
        }


        /**
         * RxJava 有四个基本概念：Observable (可观察者，即被观察者)、 Observer (观察者)、 subscribe (订阅)、事件。Observable 和 Observer 通过 subscribe() 方法实现订阅关系
         * 除了 Observer 接口之外，RxJava 还内置了一个实现了 Observer 的抽象类：Subscriber。 Subscriber 对 Observer 接口进行了一些扩展
         */

        /**
         * 当.repeat()接收到.onCompleted()事件后触发重订阅。
         * <p>
         * 当.retry()接收到.onError()事件后触发重订阅。
         */
        public void retry() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onError(new Throwable("ee"));
                        }
                }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                                return throwableObservable.onErrorReturnItem(new Throwable("error"));
                        }
                }).onErrorReturn(new Function<Throwable, String>() {
                        @Override
                        public String apply(Throwable throwable) throws Exception {
                                return "rroor";
                        }
                }).subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {

                                }
                        });
        }


        /**
         * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
         * <p>
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
         * <p>
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次
         */
        public void testSchedule() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                        }
                }).subscribeOn(Schedulers.io())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .doOnNext(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "1accept: " + Thread.currentThread().getName());
                                }
                        })
                        .observeOn(Schedulers.io()).doOnNext(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                                Log.e(TAG, "2accept: " + Thread.currentThread().getName());
                        }
                })
                        .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                        Log.e(TAG, "3accept: " + Thread.currentThread().getName());
                                }
                        });
        }

        /**
         * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
         Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
         Schedulers.newThread() 代表一个常规的新线程
         AndroidSchedulers.mainThread() 代表Android的主线程
         Schedulers.immediate()直接在当前线程运行，相当于不指定线程，默认的Scheduler
         */
        /**
         * observeOn() 指定的是它之后的操作所在的线程。因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可。
         * subscribeOn() 的位置放在哪里都可以，但它是只能调用一次的。

         Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
         .subscribeOn(Schedulers.io())
         .observeOn(Schedulers.newThread())
         .map(mapOperator) // 新线程，由 observeOn() 指定
         .observeOn(Schedulers.io())
         .map(mapOperator2) // IO 线程，由 observeOn() 指定
         .observeOn(AndroidSchedulers.mainThread)
         .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定

         observeOn()是用来指定下游observer回调发生的线程
         */


}
