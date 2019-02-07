package com.owen.designPattern.event;

import java.util.EventListener;
import java.util.EventObject;

public interface HouseKeepingEventListener extends EventListener {

    void getHouseKeepingTask(EventObject eventObject, Object arg);
}
