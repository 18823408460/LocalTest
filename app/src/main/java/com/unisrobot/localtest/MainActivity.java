package com.unisrobot.localtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.vaitech.ask.AskClient;
import cn.vaitech.ask.AskPayload;
import cn.vaitech.ask.utils.ToolKits;

public class MainActivity extends AppCompatActivity {

        private static final String TAG = "MainActivity";

        private static final String AccessKey = "bbab860664575a22788c46d36f679961";

        private static final String AccessSecret = "2CExPd5WPn8g7fkrqGAVU0qSRHsGF8";

        private static final String Url = "http://api.birobotics.io/v1/ask";

        private static final String cnonce = ToolKits.randomStr();

        private static final String userid = "123";

        @BindView(R.id.editText)
        EditText editText;

        @BindView(R.id.yes)
        Button yes;

        @BindView(R.id.no)
        Button no;

        private AskClient askClient;

        private AskPayload askPayload;

        @BindView(R.id.button)
        Button button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                ButterKnife.bind(this);
                initData();
        }

        private void initData() {
                askClient = new AskClient(AccessKey, AccessSecret);
                askPayload = new AskPayload();
        }

        //        String[] questions = {"你好", "上海今天的天气", "今天的股市怎么样","你叫什么名字","how are you ","1加1等于几"};
        String[] questions = {"你好", "发票申领", "税务申报", "税务办理"};

        private int count;

        private int lock = 0;

        @OnClick(R.id.yes)
        public void yes() {
                final String question = "第一个";
                askPayload.setQuestion(question);
                askPayload.setUserId(userid);
                askPayload.setSessionId(userid);
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                // 出错的情况下，返回的是 null
                                // run: {"errorCode":0,"message":"success","data":{"content":"你好，很高兴为您服务！","similarity":1.0,"relatedQuestions":[]}}
                                //  run: null
                                String ask = askClient.ask(Url, cnonce, askPayload);
                                Log.e(TAG, "Q: " + question);
                                Log.e(TAG, "A: " + ask);
                                lock++;
                        }
                }).start();
        }
        @OnClick(R.id.no)
        public void no() {
                final String question = "第二个";
                askPayload.setQuestion(question);
                askPayload.setUserId(userid);
                askPayload.setSessionId(userid);
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                // 出错的情况下，返回的是 null
                                // run: {"errorCode":0,"message":"success","data":{"content":"你好，很高兴为您服务！","similarity":1.0,"relatedQuestions":[]}}
                                //  run: null
                                String ask = askClient.ask(Url, cnonce, askPayload);
                                Log.e(TAG, "Q: " + question);
                                Log.e(TAG, "A: " + ask);
                                lock++;
                        }
                }).start();
        }


        @OnClick(R.id.button)
        public void sendData() {
                final String question = questions[count++ % questions.length];
                askPayload.setQuestion(question);
                askPayload.setUserId(userid);
                askPayload.setSessionId(userid);
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                // 出错的情况下，返回的是 null
                                // run: {"errorCode":0,"message":"success","data":{"content":"你好，很高兴为您服务！","similarity":1.0,"relatedQuestions":[]}}
                                //  run: null
                                String ask = askClient.ask(Url, cnonce, askPayload);
                                Log.e(TAG, "Q: " + question);
                                Log.e(TAG, "A: " + ask);
                                lock++;
                        }
                }).start();
        }
}
