package com.szchoiceway.customerui.bmw.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.szchoiceway.customerui.R;

public class VideoActivity extends AppCompatActivity {
    private static final String NOTIFY_SEND_SURFACE = "notify_send_surface";
    /* access modifiers changed from: private */
    public static final String TAG = "VideoActivity";
    boolean mPlayNext = false;
    Surface mSurface;
    SurfaceReceiver mSurfaceReceiver = new SurfaceReceiver();
    /* access modifiers changed from: private */
    public TextureView mVideoTextureView;
    FrameLayout surfaceContainer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_video);
        this.mSurfaceReceiver.registerRec();
        this.surfaceContainer = (FrameLayout) findViewById(R.id.surfaceContainer);
        init();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        if (this.mPlayNext) {
            this.mPlayNext = false;
            init();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mSurfaceReceiver.unregisterRec();
    }

    /* access modifiers changed from: private */
    public void init() {
        this.surfaceContainer.removeAllViews();
        TextureView textureView = new TextureView(this);
        this.mVideoTextureView = textureView;
        this.surfaceContainer.addView(textureView);
        this.mVideoTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                VideoActivity.this.mPlayNext = false;
                if (surfaceTexture != null) {
                    Surface surface = new Surface(surfaceTexture);
                    Log.i(VideoActivity.TAG, "onSurfaceTextureAvailable: gegwgwegew  videoTextureView");
                    VideoActivity.this.mSurface = surface;
                    VideoActivity videoActivity = VideoActivity.this;
                    videoActivity.sendSurface(videoActivity.mSurface);
                    Log.i(VideoActivity.TAG, "onSurfaceTextureDestroyedsegege: create   " + VideoActivity.this.mVideoTextureView.hashCode());
                }
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                Log.i(VideoActivity.TAG, "onSurfaceTextureDestroyed: ");
                VideoActivity.this.mSurface = null;
                VideoActivity.this.sendSurface((Surface) null);
                Log.i(VideoActivity.TAG, "onSurfaceTextureDestroyedsegege: " + VideoActivity.this.mVideoTextureView.hashCode());
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendSurface(Surface surface) {
        Intent intent = new Intent("sendSurface_a");
        Bundle bundle = new Bundle();
        bundle.putParcelable("Surface", surface);
        intent.putExtra("BundleValue", bundle);
        sendBroadcast(intent);
    }

    public class SurfaceReceiver extends BroadcastReceiver {
        private boolean isReg = false;

        public SurfaceReceiver() {
        }

        public void registerRec() {
            if (!this.isReg) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(VideoActivity.NOTIFY_SEND_SURFACE);
                intentFilter.addAction("remove");
                intentFilter.addAction("playnext");
                VideoActivity.this.registerReceiver(this, intentFilter);
                this.isReg = true;
            }
        }

        public void unregisterRec() {
            if (this.isReg) {
                VideoActivity.this.unregisterReceiver(this);
                this.isReg = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(VideoActivity.NOTIFY_SEND_SURFACE)) {
                Log.i("TAG", "onReceive: NOTIFY_SEND_SURFACE gegwgwegew");
                VideoActivity.this.init();
            } else if (action.equals("remove")) {
                VideoActivity.this.surfaceContainer.removeAllViews();
            } else if (action.equals("playnext")) {
                VideoActivity.this.mPlayNext = true;
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
