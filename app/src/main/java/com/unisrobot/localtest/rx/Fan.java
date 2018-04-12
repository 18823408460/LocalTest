package com.unisrobot.localtest.rx;

/**
 * Created by Administrator on 2018/4/12.
 */

interface Fan<D, T> {

        void start(D d);

        void end(T t);
}
