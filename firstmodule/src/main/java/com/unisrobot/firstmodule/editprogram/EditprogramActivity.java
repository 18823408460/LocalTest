package com.unisrobot.firstmodule.editprogram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/4/24.
 */

public class EditprogramActivity extends Activity {

    private static final String TAG = "EditprogramActivity";

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
                   /// view.startDragAndDrop(dragData, shadow, null, View.DRAG_FLAG_GLOBAL);
                    return  true ;
                }else {
                    return false;
                }
            }
        });
        final TextView textView = findViewById(R.id.tv_drag);
        textView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()){
                    case DragEvent.ACTION_DROP:
                        textView.setText(event.getClipData().getItemAt(0).getText());
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }
}
