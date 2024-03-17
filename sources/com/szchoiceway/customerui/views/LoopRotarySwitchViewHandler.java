package com.szchoiceway.customerui.views;

import android.os.Handler;
import android.os.Message;

public abstract class LoopRotarySwitchViewHandler extends Handler {
    public static final int msgid = 1000;
    private boolean loop = false;
    public long loopTime = 3000;
    private Message msg = createMsg();

    public abstract void doScroll();

    public LoopRotarySwitchViewHandler(int i) {
        this.loopTime = (long) i;
    }

    public void handleMessage(Message message) {
        message.what = 1000;
        if (this.loop) {
            doScroll();
            sendMsg();
        }
        super.handleMessage(message);
    }

    public void setLoop(boolean z) {
        this.loop = z;
        if (z) {
            sendMsg();
            return;
        }
        try {
            removeMessages(1000);
        } catch (Exception unused) {
        }
    }

    private void sendMsg() {
        try {
            removeMessages(1000);
        } catch (Exception unused) {
        }
        Message createMsg = createMsg();
        this.msg = createMsg;
        sendMessageDelayed(createMsg, this.loopTime);
    }

    public Message createMsg() {
        Message message = new Message();
        message.what = 1000;
        return message;
    }

    public void setLoopTime(long j) {
        this.loopTime = j;
    }

    public long getLoopTime() {
        return this.loopTime;
    }

    public boolean isLoop() {
        return this.loop;
    }
}
