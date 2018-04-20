package android_serialport_api.intefaces;

/**
 * Created by Administrator on 2018/4/19.
 */

public interface ISerialPortCallBack {
        void onDataReceived(byte[] p1, int p2);
}
