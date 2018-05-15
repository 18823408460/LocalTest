// IMsgCallBack.aidl
package com.uurobot.aidl;

interface IMsgCallBack {
  boolean interceptRobotAction(in String result);
}
