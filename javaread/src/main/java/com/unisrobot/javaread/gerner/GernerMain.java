package com.unisrobot.javaread.gerner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/5/27.
 */

public class GernerMain {
    public static void main(String[] args) {
        ParentA parentA = new ParentA();
        SonA sonA = new SonA();
        GrandSonA grandSonA = new GrandSonA();
        List<GrandSonA> list = new ArrayList<>();
        printA(list);

        List<ParentA> list1 = new ArrayList<>();
        printB(list1);
    }

    private static void printA(List<? extends SonA> sonA) {
    }

    private static void printB(List<? super SonA> sonA) {
    }
}
