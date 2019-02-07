package com.owen.designPattern.event;

import java.util.EventListener;
import java.util.EventObject;

public interface HomeworkEventListener extends EventListener {

    void getHomework(EventObject object, Object arg);
}
