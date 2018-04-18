package com.unisrobot.comlib.log;

public interface LogStrategy {

  void log(int priority, String tag, String message);
}
