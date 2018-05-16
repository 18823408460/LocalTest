// IVoiceCallback.aidl
package com.unisrobot.robothead;

// Declare any non-default types here with import statements

interface IVoiceCallback {
    void onResult(boolean state,String data);
}
