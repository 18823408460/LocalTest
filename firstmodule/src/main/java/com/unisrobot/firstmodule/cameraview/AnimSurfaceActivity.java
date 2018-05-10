package com.unisrobot.firstmodule.cameraview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.unisrobot.firstmodule.R;
import com.unisrobot.firstmodule.play.ContentType;
import com.unisrobot.firstmodule.play.IPlayEnd;
import com.unisrobot.firstmodule.play.PlayItem;
import com.unisrobot.firstmodule.play.PlayerKitVer;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WEI on 2018/5/6.
 */

public class AnimSurfaceActivity extends AppCompatActivity {
        ImageView imageView;
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
        int index;
        private Bitmap[] bitmap;

        private static final float hightBattery = 24.8f;// 最高电压

        private static final float lowBattery = 18f;// 最低电压
        PlayerKitVer playerKitVer = new PlayerKitVer();
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                testRunnalb();
        }



        private void testRunnalb() {
                setContentView(R.layout.first_module_activity_ruunable);
                Button button = findViewById(R.id.btn_runnable);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Log.e("PlayerKitVer", "onClick: -----------------------------------------------------" );
                                PlayItem playItem = new PlayItem(ContentType.TEXT, "第三个", new Runnable() {
                                        @Override
                                        public void run() {
                                                Log.e("PlayerKitVer", "run: 第三个 " + Thread.currentThread().getName());
                                                try {
                                                        Thread.sleep(10000);
                                                } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                        Log.e("PlayerKitVer", "play: e="+e );
                                                }
                                        }
                                });
                                List<PlayItem> playItems = new ArrayList<>();
                                playItems.add(playItem);
                                playerKitVer.playItems(playItems, new IPlayEnd() {
                                        @Override
                                        public void onPlayEnd() {
                                                Log.e("PlayerKitVer", "----end0---------");
                                        }
                                });
                        }
                });
                PlayItem playItem = new PlayItem(ContentType.TEXT, "第一个", new Runnable() {
                        @Override
                        public void run() {
                                Log.e("PlayerKitVer", "run: 第一个 " + Thread.currentThread().getName());
                                try {
                                        Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        Log.e("PlayerKitVer", "play: e="+e );
                                }
                        }
                });
                PlayItem playItem2 = new PlayItem(ContentType.TEXT, "第二个", new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        Thread.sleep(10000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        Log.e("PlayerKitVer", "play: e="+e );
                                }
                                Log.e("PlayerKitVer", "run: 第二个 ");
                        }
                });
                List<PlayItem> playItems = new ArrayList<>();
                playItems.add(playItem);
                playItems.add(playItem2);

                playerKitVer.playItems(playItems, new IPlayEnd() {
                        @Override
                        public void onPlayEnd() {
                                Log.e("PlayerKitVer", "onPlayEnd: ");
                        }
                });
        }

        private void testRuleView() {
                setContentView(new TimerView(this));
        }

        public static int getUnChargingPer(float battery) {
                float diff = 0.0f;
                diff = (battery - lowBattery) / (hightBattery - lowBattery);
                if (diff >= 1) {
                        diff = 1;
                }
                return (int) (diff * 100);
        }


        public void onClick(View view) {
                startActivity(new Intent(this, ChildActivity.class));
        }


        private void testCanvas() {
                setContentView(R.layout.first_module_activity_canvas);
        }


        int number = 1000;

        private static final String TAG = "AnimSurfaceActivity";

        private void testdecodeResource() {
                bitmap = new Bitmap[number];
                Trace.beginSection("decodeBitmap");
                for (int i = 0; i < number; i++) {
                        Log.e(TAG, "测试第" + (i + 1) + "张图片");
                        bitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.first_module_home_normal); // 测试第153张图片 OOM
                        //            bitmap[i] = BitmapFactory.decodeStream(getResources().openRawResource(+R.drawable.first_module_home_normal)); // 测试第467张图片
                        //            BitmapFactory.decodeStream()
                        //            BitmapLruCache.getBitmapLruCache().putBitmap(i,bitm);
                }
                Trace.endSection();
                for (int i = 0; i < number; i++) {
                        BitmapLruCache.getBitmapLruCache().getBitmap(i);
                }
        }


        private void testRotateAnim() {
                setContentView(R.layout.first_module_activity_roateanim);
        }


        private void testMatrix() {
                setContentView(R.layout.first_module_activity_matrix);
        }

        private void testAnimSurface() {
                setContentView(R.layout.first_module_activity_animsurface);
        }

        private void testLrcCahche() {
                setContentView(R.layout.first_module_activity_lrccache);
                imageView = findViewById(R.id.img_lrc);

                Button button = findViewById(R.id.btn_lru);

                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                imageView.setImageBitmap(BitmapLruCache.getBitmapLruCache().getBitmap(bitmaps[index++ % bitmaps.length], getApplicationContext()));
                        }
                });
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                Log.e(TAG, "onDestroy: ");
        }
}
