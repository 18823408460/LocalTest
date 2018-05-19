package com.unisrobot.javaread.rx;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Administrator on 2018/5/17.
 */

public class RxMain {

        @Test
        public void test1() {
                Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext("hello");
                                //e.onComplete();
                                e.onNext("complete");

                                // 为什么这里还会调用 (这个和onComplete的调用对  订阅者的影响 )
                                e.onError(new Throwable("error"));
                        }
                }).subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                                System.out.println("onSubscribe");
                        }

                        @Override
                        public void onNext(String s) {
                                System.out.println("onNext=" + s);
                        }

                        @Override
                        public void onError(Throwable e) {
                                System.out.println("onError=" + e);
                        }

                        @Override
                        public void onComplete() {
                                System.out.println("onComplete");
                        }
                });

                Observable.create(e -> {
                        e.onNext("hello22");
                        e.onComplete();
                        e.onNext("complete");
                }).subscribe(System.out::println);
        }

        @Test
        public void test2() {
                PublishSubject.create(   (e) -> e.onNext("ehllo"))
                        .subscribe();

        }
}
