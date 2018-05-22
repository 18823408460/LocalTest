package com.unisrobot.firstmodule.metrial;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

/**
 * GridView 间隔，自定义垂直进度条
 */
public class GradviewActivity extends Activity {
        volatile
        private RecyclerView recyclerView;
        private static final String TAG = "GradviewActivity";
        private List<String> list = Arrays.asList(
                "今天是个很美好的日子，可以太阳太大了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "明天的天气应该会慢慢变好，阴天是最后不过了",
                "后天呢，后天。。。。。。",
                "大后天呢，大后天。。。。。。"

        );

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                testDraw();

        }

        private void testDraw(){
                setContentView(R.layout.first_module_activity_drawtest);
        }

        private void recycleViewTest() {
                setContentView(R.layout.first_module_activity_gradview);

                recyclerView = findViewById(R.id.first_module_grid_recycleview);
                // 列数
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                // 添加分割线
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.first_module_drawable_item));
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(new RecyclerView.Adapter() {
                        @NonNull
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                Log.e(TAG, "onCreateViewHolder: " );
                                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                                View inflate = layoutInflater.inflate(R.layout.first_module_grid_item, null);
                                MyHolder myHolder = new MyHolder(inflate);
                                return myHolder;
                        }

                        @Override
                        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                                TextView textView = (TextView) holder.itemView;
                                Log.e(TAG, "onBindViewHolder: " );
                                textView.setText(list.get(position));
                        }

                        @Override
                        public int getItemCount() {
                                Log.e(TAG, "getItemCount: " );
                                return list.size();
                        }
                });
        }


        private class MyHolder extends RecyclerView.ViewHolder {
                public TextView view;

                public MyHolder(View itemView) {
                        super(itemView);
                        view = itemView.findViewById(R.id.grid_tv);
                }
        }
}
