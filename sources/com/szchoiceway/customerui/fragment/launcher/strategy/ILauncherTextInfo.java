package com.szchoiceway.customerui.fragment.launcher.strategy;

import android.widget.TextView;

public interface ILauncherTextInfo {
    TextView getBtSubtitleTextView() {
        return null;
    }

    TextView getMusicSubtitleTextView() {
        return null;
    }

    void setMusicInfo(String str) {
        TextView musicSubtitleTextView = getMusicSubtitleTextView();
        if (musicSubtitleTextView != null) {
            musicSubtitleTextView.setFocusable(true);
            musicSubtitleTextView.setSelected(true);
            musicSubtitleTextView.setText(str);
        }
    }

    void setBtInfo(String str) {
        if (getBtSubtitleTextView() != null) {
            getBtSubtitleTextView().setText(str);
        }
    }
}
