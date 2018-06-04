package com.uurobot.baseframe.bean.shangcheng;

/**
 * Created by WEI on 2018/6/1.
 */

/**
 * 购物车需要的信息 bean,  Intent传递的数据必须序列化
 */
public class GoodsCarBean extends GoodsBean {

        public GoodsCarBean(GoodsBean goodsBean) {
                this.select = false;
                this.setName(goodsBean.getName());
                this.setCover_price(goodsBean.getCover_price());
                this.setFigure(goodsBean.getFigure());
                this.setOrigin_price(goodsBean.getOrigin_price());
                this.setProduct_id(goodsBean.getProduct_id());
        }

        private boolean select;

        public boolean isSelect() {
                return select;
        }

        public void setSelect(boolean select) {
                this.select = select;
        }

        @Override
        public String toString() {
                return super.toString() + "   GoodsCarBean{" +
                        "select=" + select +
                        '}';
        }
}
