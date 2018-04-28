package com.unisrobot.firstmodule.editprogram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.R;

import java.util.Collections;

/**
 * Created by WEI on 2018/4/24.
 */

/**
 * recycleView item的拖拽
 */
public class GragActivity extends Activity {
        private RecyclerView recyclerView;
        private static final String TAG = "GragActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_drag);
                recyclerView = findViewById(R.id.first_module_recycleview_drag);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                final MyGragAdapter myGragAdapter = new MyGragAdapter(this);
                recyclerView.setAdapter(myGragAdapter);
                recyclerView.addItemDecoration(new SpacesItemDecoration(40));
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
                        @Override
                        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                                Log.e(TAG, "getMovementFlags: ");
                                // 网格布局和 列表布局的移动方向是不一样的
                                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                                        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                                                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                                        final int swipeFlags = 0;
                                        return makeMovementFlags(dragFlags, swipeFlags);
                                } else {
                                        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                                        final int swipeFlags = 0;
                                        return makeMovementFlags(dragFlags, swipeFlags);
                                }
                        }

                        @Override
                        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                                //得到当拖拽的viewHolder的Position
                                int fromPosition = viewHolder.getAdapterPosition();
                                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                                //拿到当前拖拽到的item的viewHolder
                                int toPosition = target.getAdapterPosition();
                                if (fromPosition < toPosition) {
                                        for (int i = fromPosition; i < toPosition; i++) {
                                                Collections.swap(myGragAdapter.getArrayList(), i, i + 1);
                                        }
                                } else {
                                        for (int i = fromPosition; i > toPosition; i--) {
                                                Collections.swap(myGragAdapter.getArrayList(), i, i - 1);
                                        }
                                }
                                myGragAdapter.notifyItemMoved(fromPosition, toPosition);
                                return true ;
                        }

                        @Override
                        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                                Log.e(TAG, "onSwiped: ");
                        }

                        @Override
                        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                                super.onSelectedChanged(viewHolder, actionState);
                        }

                        @Override
                        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                                super.clearView(recyclerView, viewHolder);
                        }
                });
                itemTouchHelper.attachToRecyclerView(recyclerView);

                // 怎么设置 Item 的监听
        }
}
