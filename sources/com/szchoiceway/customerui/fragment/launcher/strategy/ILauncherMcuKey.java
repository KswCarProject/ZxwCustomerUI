package com.szchoiceway.customerui.fragment.launcher.strategy;

public interface ILauncherMcuKey {
    int getDownNextMainFocusIndex(int i) {
        return -1;
    }

    int getLeftNextMainFocusIndex(int i) {
        return -1;
    }

    int getRightNextMainFocusIndex(int i) {
        return -1;
    }

    int getUpNextMainFocusIndex(int i) {
        return -1;
    }

    boolean isDownMcuKeyEnabled() {
        return false;
    }

    boolean isLeftMcuKeyEnabled() {
        return false;
    }

    boolean isRightMcuKeyEnabled() {
        return false;
    }

    boolean isUpMcuKeyEnabled() {
        return false;
    }
}
