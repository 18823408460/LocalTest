package com.unisrobot.javaread.designMode.ObservalPatter.step1;

/**
 * Created by WEI on 2018/5/12.
 */
// 主题。。。。(=)
public interface Subject {
        void registerObserver(Observers observer);

        void removeObserver(Observers observer);

        void notifyObservers();
}
