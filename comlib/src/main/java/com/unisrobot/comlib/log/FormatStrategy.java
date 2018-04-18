package com.unisrobot.comlib.log;

public interface FormatStrategy {

  void log(int priority, String tag, String message);
}
