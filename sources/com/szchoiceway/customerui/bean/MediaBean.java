package com.szchoiceway.customerui.bean;

import android.graphics.Bitmap;
import com.szchoiceway.customerui.utils.TimeHelps;
import java.io.Serializable;
import java.util.Locale;

public class MediaBean implements Serializable {
    private boolean isMediaPlay;
    private boolean isShowUIMediaInfo = true;
    private int keyCode = 0;
    private String mediaAlbum;
    private String mediaAlbumArtist;
    private Bitmap mediaAlbumBitmap;
    private String mediaArtist;
    private ComponentBean mediaComponent;
    private long mediaCurrentTimeMillisecond;
    private String mediaSinger;
    private String mediaTitle;
    private long mediaTotalTimeMillisecond;
    private int mediaType;
    private int mediaValidLoopMode;
    private String playingAppPackageName;

    public boolean isShowUIMediaInfo() {
        return this.isShowUIMediaInfo;
    }

    public MediaBean setShowUIMediaInfo(boolean z) {
        this.isShowUIMediaInfo = z;
        return this;
    }

    public MediaBean setKeyCode(int i) {
        this.keyCode = i;
        return this;
    }

    public MediaBean setMediaAlbumArtist(String str) {
        this.mediaAlbumArtist = str;
        return this;
    }

    public String getMediaAlbumArtist() {
        return this.mediaAlbumArtist;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public MediaBean setMediaComponent(ComponentBean componentBean) {
        this.mediaComponent = componentBean;
        return this;
    }

    public ComponentBean getMediaComponent() {
        if (this.mediaComponent == null) {
            this.mediaComponent = new ComponentBean("");
        }
        return this.mediaComponent;
    }

    public String getKey() {
        return getMediaComponent().getKey();
    }

    public MediaBean setMediaTotalTimeMillisecond(long j) {
        this.mediaTotalTimeMillisecond = j;
        return this;
    }

    public MediaBean setMediaCurrentTimeMillisecond(long j) {
        this.mediaCurrentTimeMillisecond = j;
        return this;
    }

    public MediaBean setMediaValidLoopMode(int i) {
        this.mediaValidLoopMode = i;
        return this;
    }

    public long getMediaTotalTimeMillisecond() {
        return this.mediaTotalTimeMillisecond;
    }

    public long getMediaCurrentTimeMillisecond() {
        return this.mediaCurrentTimeMillisecond;
    }

    public String getMediaTotalTimeStr() {
        return TimeHelps.getPlayTime(this.mediaTotalTimeMillisecond / 1000);
    }

    public String getMediaCurrentTimeStr() {
        return TimeHelps.getPlayTime(this.mediaCurrentTimeMillisecond / 1000);
    }

    public int getMediaValidLoopMode() {
        return this.mediaValidLoopMode;
    }

    public int getMediaType() {
        return this.mediaType;
    }

    public MediaBean setMediaType(int i) {
        this.mediaType = i;
        return this;
    }

    public MediaBean setMediaTitle(String str) {
        this.mediaTitle = str;
        return this;
    }

    public MediaBean setMediaAlbum(String str) {
        this.mediaAlbum = str;
        return this;
    }

    public MediaBean setMediaAlbumBitmap(Bitmap bitmap) {
        this.mediaAlbumBitmap = bitmap;
        return this;
    }

    public MediaBean setPlayingAppPackageName(String str) {
        this.playingAppPackageName = str;
        return this;
    }

    public MediaBean setMediaArtist(String str) {
        this.mediaArtist = str;
        return this;
    }

    public String getMediaTitle() {
        return this.mediaTitle;
    }

    public String getMediaAlbum() {
        return this.mediaAlbum;
    }

    public String getMediaArtist() {
        return this.mediaArtist;
    }

    public Bitmap getMediaAlbumBitmap() {
        return this.mediaAlbumBitmap;
    }

    public String getPlayingAppPackageName() {
        return this.playingAppPackageName;
    }

    public MediaBean setMediaSinger(String str) {
        this.mediaSinger = str;
        return this;
    }

    public MediaBean setMediaPlay(boolean z) {
        this.isMediaPlay = z;
        return this;
    }

    public String getMediaSinger() {
        return this.mediaSinger;
    }

    public boolean isMediaPlay() {
        return this.isMediaPlay;
    }

    public String toString() {
        return " getMediaComponent=" + getMediaComponent().toString() + " ,mediaTitle=" + this.mediaTitle + " ,isMediaPlay=" + this.isMediaPlay + " ,mediaType=" + this.mediaType + " ,getMediaCurrentTimeStr=" + getMediaCurrentTimeStr() + " ,getMediaTotalTimeStr=" + getMediaTotalTimeStr();
    }

    public static String stringFormat(String str, Object... objArr) {
        return String.format(Locale.CHINA, str, objArr);
    }
}
