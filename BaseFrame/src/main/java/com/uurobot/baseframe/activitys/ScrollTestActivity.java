package com.uurobot.baseframe.activitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uurobot.baseframe.R;

/**
 * scrollTo(int x, int y) 是将View中内容滑动到相应的位置，参考的坐标系原点为parent View的左上角。
 * 与英文中To与By的区别类似，scrollTo(x, y)标识移动到一个具体的坐标点(x, y)，
 * 而scrollBy(dx, dy)表示移动的增量为dx、dy
 * scrollTo、scrollBy方法移动的是View的content
 * <p>
 * <p>
 * <p>
 * 2. 如果在ViewGroup中使用scrollTo、scrollBy方法，那么移动的将是所有子View，但如果在View中使用，那么移动的将是View的内容
 * <p>
 * <p>
 * 3. mScrollX：表示离视图起始位置的x水平方向的偏移量
 * mScrollY：表示离视图起始位置的y垂直方向的偏移量
 * ：mScrollX和mScrollY指的并不是坐标，而是偏移量。
 *
 * 我要移动view到坐标点（100，100），那么我的偏移量就是(0,，0)  - （100，100） = （-100 ，-100）  ，我就要执行view.scrollTo(-100,-100),达到这个效果。
 */
public class ScrollTestActivity extends Activity {
        private static final String TAG = "ScrollTestActivity";
        private Button btn_scrollTo;
        private Button btn_scrollBy;
        private ImageView imageView;
        private ImageView imageView2;
        private Button btn_move1;
        private Button btn_move2;
        private LinearLayout ll;
        private int count = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_scroll_test);
                ll = findViewById(R.id.ll);
                btn_move1 = findViewById(R.id.btn_move1);
                btn_move2 = findViewById(R.id.btn_move2);
                btn_scrollTo = findViewById(R.id.scrollTo);
                btn_scrollTo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                count += 2;
                                btn_move1.scrollBy(1 + count, 0);
                                Log.e(TAG, "onClick: " + btn_move1.getScrollX() + "   " + btn_move1.getScrollY());
                        }
                });
                btn_scrollBy = findViewById(R.id.scrollBy);
                btn_scrollBy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                count += 2;
                                ll.scrollTo(1 + count, 0);
                                Log.e(TAG, "11onClick: " + btn_move1.getScrollX() + "   " + btn_move1.getScrollY());
                        }
                });

                imageView = findViewById(R.id.image_scroll);
                imageView2 = findViewById(R.id.image_scroll2);
                imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                Log.e(TAG, "onGlobalLayout: " + imageView.getLeft() + "   " + imageView.getRight());
                                Log.e(TAG, "onGlobalLayout: " + imageView.getTranslationX() + "   " + imageView.getTranslationY());
                                Log.e(TAG, "onGlobalLayout: " + imageView.getScrollX() + "   " + imageView.getScrollY());
                        }
                });
        }
}
