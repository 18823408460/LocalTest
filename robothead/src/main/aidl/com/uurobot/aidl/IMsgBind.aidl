// IMsgBind.aidl
package com.uurobot.aidl;
import com.uurobot.aidl.IMsgCallBack ;

interface IMsgBind {
    void send(in String dataJson);
    void registerCallBack(IMsgCallBack callback);
    void unRegisterCallBack(IMsgCallBack callback);
}