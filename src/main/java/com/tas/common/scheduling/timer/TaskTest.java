package com.tas.common.scheduling.timer;

import java.util.TimerTask;

public class TaskTest extends TimerTask {
    TaskTimer taskTimer;

    public TaskTest(TaskTimer taskTimer) {
        this.taskTimer = taskTimer;
    }

    @Override
    public void run() {
//        taskTimer.runTask();
    }
}
