package com.uurobot.baseframe.bean.shangcheng;

/**
 * Created by WEI on 2018/6/1.
 */

import java.io.Serializable;

/**
 * 购物车需要的信息 bean,  Intent传递的数据必须序列化
 */
public class GoodsBean implements Serializable {
    private String cover_price;
    private String figure;
    private String name;
    private String origin_price;
    private String product_id;
    private int numbers;



    


}
