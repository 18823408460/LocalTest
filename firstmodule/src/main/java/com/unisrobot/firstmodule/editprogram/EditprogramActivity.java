package com.unisrobot.firstmodule.editprogram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/24.
 */

public class EditprogramActivity extends Activity {
        @BindView(R2.id.editprogram_img)
        ImageView editprogramImg;
        private static final String TAG = "EditprogramActivity";
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_editprogram);
                ButterKnife.bind(this);
                
                editprogramImg.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                                Log.e(TAG, "onLongClick: " );
//                                v.startDragAndDrop()
                                return true;
                        }
                });
        }
}
