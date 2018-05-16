// IVoiceBinder.aidl
package com.unisrobot.robothead;
import com.unisrobot.robothead.IVoiceCallback;
// Declare any non-default types here with import statements

interface IVoiceBinder {
    void startRecogniger(IVoiceCallback  callback);
    void stopRecognizer();
}
