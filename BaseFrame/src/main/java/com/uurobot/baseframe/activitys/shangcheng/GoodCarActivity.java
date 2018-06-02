package com.uurobot.baseframe.activitys.shangcheng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.activitys.BaseActivity;

/**
 * Created by Administrator on 2018/6/2.
 */
// 在Android中如果想要实现3D效果一般有两种选择，一是使用Open GL ES，二是使用Camera。Open GL ES使用起来太过复杂，
// 一般是用于比较高级的3D特效或游戏，像比较简单的一些3D效果，使用Camera就足够了
public class GoodCarActivity extends BaseActivity {
        private RecyclerView recyclerView;
        private ImageButton imageButtonEdit;
        private LinearLayout linearLayoutCaculate;
        private LinearLayout linearLayoutEdit;
        private LinearLayout linearLayoutEmpty;
        private LinearLayout linearLayoutNotEmpty;
        private LayoutInflater layoutInflater;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_good_car);
                initView();
        }

        private void initView() {
                recyclerView = findViewById(R.id.recycleview_goods_car);
                imageButtonEdit = findViewById(R.id.imageButton_goods_car_edit);
                linearLayoutCaculate = findViewById(R.id.layout_bottom_goods_car_caculate);
                linearLayoutEdit = findViewById(R.id.layout_bottom_goods_car_edit);
                linearLayoutEmpty = findViewById(R.id.layout_goods_car_empty);
                linearLayoutNotEmpty = findViewById(R.id.layout_goods_car_not_empty);
                layoutInflater = LayoutInflater.from(this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setAdapter(new RecyclerView.Adapter() {
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                View view = layoutInflater.inflate(R.layout.recycleview_item_goods_car, null);
                                return null;
                        }

                        @Override
                        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                        }

                        @Override
                        public int getItemCount() {
                                return 0;
                        }
                });
        }
}
