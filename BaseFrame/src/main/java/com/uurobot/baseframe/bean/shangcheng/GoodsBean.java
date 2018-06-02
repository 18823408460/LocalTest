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

        public GoodsBean() {
        }

        public String getCover_price() {
                return cover_price;
        }

        public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
        }

        public String getFigure() {
                return figure;
        }

        public void setFigure(String figure) {
                this.figure = figure;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getOrigin_price() {
                return origin_price;
        }

        public void setOrigin_price(String origin_price) {
                this.origin_price = origin_price;
        }

        public String getProduct_id() {
                return product_id;
        }

        public void setProduct_id(String product_id) {
                this.product_id = product_id;
        }

        public int getNumbers() {
                return numbers;
        }

        public void setNumbers(int numbers) {
                this.numbers = numbers;
        }

        @Override
        public String toString() {
                return "GoodsBean{" +
                        "cover_price='" + cover_price + '\'' +
                        ", figure='" + figure + '\'' +
                        ", name='" + name + '\'' +
                        ", origin_price='" + origin_price + '\'' +
                        ", product_id='" + product_id + '\'' +
                        ", numbers=" + numbers +
                        '}';
        }
}
