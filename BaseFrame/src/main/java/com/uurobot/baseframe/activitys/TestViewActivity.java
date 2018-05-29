package com.uurobot.baseframe.activitys;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.drawable.CircleDrawable;
import com.uurobot.baseframe.drawable.RoundRectDrawable;
import com.uurobot.baseframe.view.RotateThreeView;
import com.uurobot.baseframe.view.SurfaceViewAnim;

import java.util.Timer;
import java.util.TimerTask;

import static android.net.wifi.WifiConfiguration.Status.strings;

/**
 * Created by WEI on 2018/5/26.
 */

public class TestViewActivity extends BaseActivity {
        private ImageView imageView;

        @SuppressLint("ResourceType")
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                //                testAnim();

                //                testDrawable();

                //                testRotateView();
                testPopupWindow();

        }

        private void testPopupWindow() {
                setContentView(R.layout.activity_textview);
                Button button1 = findViewById(R.id.btn_activity_testview);
                button1.setText("水电费是否水电费水电费");
                button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
                                ImageView contentView = new ImageView(getApplicationContext());
                                contentView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                popupWindow.dismiss();
                                        }
                                });
                                contentView.setBackgroundResource(R.drawable.channel1);
                                popupWindow.setContentView(contentView);
                                //  popupWindow.showAtLocation(v, Gravity.CENTER,0,0); //屏幕居中位置
                                //  popupWindow.showAsDropDown(v); // v 的左下角
                                popupWindow.showAsDropDown(v, v.getWidth() / 2 - popupWindow.getWidth() / 2, 0, -1);
                        }
                });
        }

        private void testViewFlipper() {
                final int ids[] = {R.drawable.channel1, R.drawable.channel2, R.drawable.channel3, R.drawable.channel4};
                ViewFlipper viewFlipper = new ViewFlipper(this);
                for (int i = 0; i < 4; i++) {
                        ImageView imageView1 = new ImageView(this);
                        imageView1.setBackgroundResource(ids[i]);
                        viewFlipper.addView(imageView1);
                }
                for (int i = 0; i < 4; i++) {
                        TextView textView = new TextView(this);
                        textView.setTextColor(Color.parseColor("#ff0000"));
                        textView.setTextSize(30);
                        textView.setText("sdfsfd " + Math.random());
                        viewFlipper.addView(textView);
                }
                setContentView(viewFlipper);
                viewFlipper.setFlipInterval(2000);
                viewFlipper.setAutoStart(true);
                viewFlipper.startFlipping();

        }

        private void testImageSwitcher() {
                final ImageSwitcher imageSwitcher = new ImageSwitcher(this);
                imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                                ImageView imageView = new ImageView(getApplicationContext());
                                return imageView;
                        }
                });
                setContentView(imageSwitcher);
                final int ids[] = {R.drawable.channel1, R.drawable.channel2, R.drawable.channel3};
                new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                imageSwitcher.setBackgroundResource(ids[(int) (ids.length * Math.random())]);
                                        }
                                });
                        }
                }, 1000, 1000);
        }

        private void testTextSwitcher() {
                final TextSwitcher textSwitcher = new TextSwitcher(this);
                textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                        @Override
                        public View makeView() {
                                TextView textView = new TextView(getApplicationContext());
                                textView.setTextColor(Color.parseColor("#ff0000"));
                                textView.setTextSize(30);
                                return textView;
                        }
                });
                final String[] strings = {"11", "22", "33", "44"};
                setContentView(textSwitcher);
                new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                textSwitcher.setText(strings[(int) (strings.length * Math.random())]);
                                        }
                                });
                        }
                }, 1000, 1000);
        }

        private void testDrawable() {
                setContentView(R.layout.activity_textview);
                imageView = findViewById(R.id.image_test);
                @SuppressLint("ResourceType")
                Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.timg));
                //imageView.setImageDrawable(new RoundRectDrawable(bitmap));
                imageView.setImageDrawable(new CircleDrawable(bitmap));
        }

        private void testRotateView() {
                setContentView(new RotateThreeView(this));
        }

        private void testAnim() {
                setContentView(new SurfaceViewAnim(this));
        }
}
