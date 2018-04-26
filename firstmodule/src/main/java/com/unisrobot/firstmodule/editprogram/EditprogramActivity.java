package com.unisrobot.firstmodule.editprogram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/4/24.
 */

public class EditprogramActivity extends Activity {

        private static final String TAG = "EditprogramActivity";
        private static final String[]  datas= new String[]{"ssdfs","sdfdsfsdfsd","我是谁","sdfdsfsdweeeeeeee"};
        int count = 1 ;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_editprogram);

                final Button btn_drag = findViewById(R.id.btn_drag);
                btn_drag.setTag("btnDrag");
                btn_drag.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        ClipData.Item item = new ClipData.Item(view.getTag().toString());
                                        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                                        ClipData dragData = new ClipData(view.getTag().toString(), mimeTypes, item);
                                        View.DragShadowBuilder shadow = new View.DragShadowBuilder(btn_drag);

                                        //(ClipData data, DragShadowBuilder shadowBuilder,Object myLocalState, int flags)
                                        //(ClipData data, DragShadowBuilder shadowBuilder,Object myLocalState, int flags)
                                        // startDragDrop()
                                        view.startDrag(dragData, shadow, null, View.DRAG_FLAG_GLOBAL);
                                        return true;
                                } else {
                                        return false;
                                }
                        }
                });
                final TextView textView = findViewById(R.id.tv_drag);
                textView.setOnDragListener(new View.OnDragListener() {
                        @Override
                        public boolean onDrag(View v, DragEvent event) {
                                switch (event.getAction()) {
                                        case DragEvent.ACTION_DROP:
                                                textView.setText(event.getClipData().getItemAt(0).getText());
                                                break;
                                        default:
                                                break;
                                }
                                return true;
                        }
                });


                AutoNextLineLinearlayout   linearLayout = findViewById(R.id.ll_drap);
                linearLayout.setOnDragListener(new View.OnDragListener() {
                        @Override
                        public boolean onDrag(View v, DragEvent event) {
                                switch (event.getAction()) {
                                        case DragEvent.ACTION_DROP: // 拖拽到view范围中，松开那一刻触发，（不在view范围中松开，不会触发。）
                                                Log.e(TAG, "onDrag: ACTION_DROP");
                                                v.setBackgroundColor(Color.BLUE);
                                                CharSequence text = event.getClipData().getItemAt(0).getText();
                                                Button button = new Button(v.getContext());
                                                button.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                button.setText(datas[count++% datas.length] + "  "+text);
                                                button.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                                Button btn = (Button) v;
                                                                Toast.makeText(v.getContext(),btn.getText(),Toast.LENGTH_SHORT).show();
                                                        }
                                                });
                                                ((AutoNextLineLinearlayout) v).addView(button);
                                                break;
                                        case DragEvent.ACTION_DRAG_STARTED: // 拖拽的那个view开始被拖动时，这里也可以监听
                                                Log.e(TAG, "ACTION_DRAG_STARTED: ");
                                                break;

                                        case DragEvent.ACTION_DRAG_ENTERED: // 开始进入界面
                                                v.setBackgroundColor(Color.GREEN);
                                                Log.e(TAG, "ACTION_DRAG_ENTERED: ");
                                                break;

                                        case DragEvent.ACTION_DRAG_ENDED:
                                                Log.e(TAG, "ACTION_DRAG_ENDED: "); //  拖拽的那个view最终弹回到起始位置。

                                                break;
                                        case DragEvent.ACTION_DRAG_EXITED:
                                                Log.e(TAG, "ACTION_DRAG_EXITED: "); // 离开界面
                                                v.setBackgroundColor(Color.BLUE);
                                                break;
                                        case DragEvent.ACTION_DRAG_LOCATION: // 在 view 范围中拖拽时
                                                Log.e(TAG, "ACTION_DRAG_LOCATION: ");
                                                break;
                                        default:
                                                break;
                                }
                                return true;
                        }
                });

        }
}
