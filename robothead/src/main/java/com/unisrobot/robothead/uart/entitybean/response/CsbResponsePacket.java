package com.unisrobot.robothead.uart.entitybean.response;

import com.unisrobot.robothead.uart.entitybean.McuResponsePacket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/5/11.
 */

public class CsbResponsePacket extends McuResponsePacket<String> {
        private static final String TAG = "CsbResponsePacket";


        @Override
        protected String decodeContent(byte[] data) {
                //Log.e(TAG, "decodeContent: " + PacketUtil.bytesToHex(data));
                int index = 0 ;
                int csb1 = ((data[index++]) << 8) | (data[index++] & 0xff); // base - left leg - front
                int csb2 = ((data[index++]) << 8) | (data[index++] & 0xff); // base - right leg - front
                int csb3 = ((data[index++]) << 8) | (data[index++] & 0xff); // base - left leg - side ? does not work
                int csb4 = ((data[index++]) << 8) | (data[index++] & 0xff); // base - right leg - side ? does not work
                int csb5 = ((data[index++]) << 8) | (data[index++] & 0xff); // body - front
                int csb6 = ((data[index++]) << 8) | (data[index++] & 0xff); // body - back
                csb1 /= 10;
                csb2 /= 10;
                csb3 /= 10;
                csb4 /= 10;
                csb5 /= 10;
                csb6 /= 10;
                JSONObject result = new JSONObject();
                try {
                        result.put("front_left_leg", csb1);
                        result.put("front_right_leg", csb2);
                        result.put("left_leg", csb3);
                        result.put("right_leg", csb4);
                        result.put("front_body", csb5);
                        result.put("back_body", csb6);
                } catch (JSONException e) {
                        e.printStackTrace();
                }
                return result.toString();
        }
}
