// IMsgBind.aidl
package com.uurobot.aidl;
import com.uurobot.aidl.IMsgCallBack ;

interface IMsgBind {
    void send(in String dataJson);

    // 这里容易内存泄露，，需要类似handler 的处理
    void registerCallBack(IMsgCallBack callback);
    void unRegisterCallBack(IMsgCallBack callback);
}