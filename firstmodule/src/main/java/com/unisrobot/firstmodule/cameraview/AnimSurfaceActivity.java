package com.unisrobot.firstmodule.cameraview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.unisrobot.firstmodule.R;

import java.lang.ref.SoftReference;

/**
 * Created by WEI on 2018/5/6.
 */

public class AnimSurfaceActivity extends AppCompatActivity {
    ImageView imageView ;
    private int[] bitmaps = {R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven,

            R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven,

            R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven,
            R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven,
            R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven,
            R.drawable.first_module_one, R.drawable.first_module_two, R.drawable.first_module_three,
            R.drawable.first_module_four, R.drawable.first_module_five, R.drawable.first_module_six, R.drawable.first_module_seven,
            R.drawable.first_module_eight, R.drawable.first_module_nine, R.drawable.first_module_ten, R.drawable.first_module_elven};
    int index ;
    private Bitmap[] bitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testMatrix();

    }

    int number = 1000;

    private static final String TAG = "AnimSurfaceActivity";
    private void testdecodeResource(){
        bitmap = new Bitmap[number];
        for (int i = 0; i < number; i++)
        {
            Log.e(TAG, "测试第" + (i+1) + "张图片");
            Bitmap bitm = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_home_normal);
//            BitmapFactory.decodeStream()
            BitmapLruCache.getBitmapLruCache().putBitmap(i,bitm);
        }

        for (int i = 0; i < number; i++)
        {
            BitmapLruCache.getBitmapLruCache().getBitmap(i);
        }
    }

    private void testMatrix(){
        setContentView(R.layout.first_module_activity_matrix);
    }

    private void testAnimSurface(){
        setContentView(R.layout.first_module_activity_animsurface);
    }

    private void testLrcCahche() {
        setContentView(R.layout.first_module_activity_lrccache);
        imageView = findViewById(R.id.img_lrc);

        Button button = findViewById(R.id.btn_lru);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageBitmap(BitmapLruCache.getBitmapLruCache().getBitmap(bitmaps[index++ % bitmaps.length],getApplicationContext()));
            }
        });
    }
}
