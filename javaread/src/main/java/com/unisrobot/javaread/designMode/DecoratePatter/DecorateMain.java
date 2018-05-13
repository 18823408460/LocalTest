package com.unisrobot.javaread.designMode.DecoratePatter;

import com.unisrobot.javaread.designMode.DecoratePatter.step2.Beverage;
import com.unisrobot.javaread.designMode.DecoratePatter.step2.tiaoliao.Mocha;
import com.unisrobot.javaread.designMode.DecoratePatter.step2.tiaoliao.Whip;
import com.unisrobot.javaread.designMode.DecoratePatter.step2.yinliao.HouseBlend;

/**
 * Created by WEI on 2018/5/12.
 * <p>
 * /**
 * 设计原则： 对扩展开放， 对修改封闭
 * <p>
 * : 当需求增加是，原有的代码不做修改，而是通过增加类来扩展功能
 */
public class DecorateMain {
    // 以 饮料为主体 ，然后在运行是 以  "调料"  来 装饰 饮料

    // ---- 以 装饰者  为 包装类，，，，
    public static void main(String[] args) {
        // 商店推出 一种 饮料...
        Beverage houseBlend = new HouseBlend();
        houseBlend = new Mocha(houseBlend); //加入mocha
        houseBlend = new Mocha(houseBlend); // 加入 mocha
        houseBlend = new Whip(houseBlend); // 加入 whip
        System.out.println("" + houseBlend.getDescription());
        System.out.println("" + houseBlend.cost());
    }
}
