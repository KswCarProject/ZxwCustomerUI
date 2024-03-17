package com.szchoiceway.customerui.bmw.recycle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.model.LauncherModel;
import com.szchoiceway.customerui.utils.EventUtils;
import com.szchoiceway.customerui.utils.SysProviderOpt;
import com.szchoiceway.eventcenter.IEventService;
import com.szchoiceway.zxwlib.view.SlashThumbSeekView;

public class MusicItem extends RecycleItemBase implements View.OnClickListener {
    private static final String TAG = "MusicItem";
    private ImageButton btnMusicNext;
    private ImageButton btnMusicPlayPause;
    private ImageButton btnMusicPrev;
    private ImageView imgBk;
    private View imgLine;
    private ImageView imgMusic;
    Context mContext;
    private Bitmap mIcon;
    private ImageView mImageEditIcon;
    private ImageView mImageIcon;
    private int mMode = 0;
    View mMusicView;
    private SlashThumbSeekView mProgress;
    private boolean mSmallMode = false;
    private TextView mTitle;
    private TextView mTvArtist;
    private TextView mTvTitle;

    public String getTag() {
        return "Music";
    }

    public View getSetView(Context context, boolean z) {
        View view;
        this.mContext = context;
        this.m_iModeSet = SysProviderOpt.getInstance(context).getRecordInteger("KESAIWEI_SYS_MODE_SELECTION", this.m_iModeSet);
        if (this.mSmallMode == z && (view = this.mMusicView) != null) {
            return view;
        }
        this.mSmallMode = z;
        if (this.m_iModeSet == 20) {
            if (z) {
                this.mMusicView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_music_item_layout_small, (ViewGroup) null);
            } else {
                this.mMusicView = LayoutInflater.from(context).inflate(R.layout.pemp_bmw_id8_music_item_layout, (ViewGroup) null);
            }
        } else if (z) {
            this.mMusicView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_music_item_layout_small, (ViewGroup) null);
        } else {
            this.mMusicView = LayoutInflater.from(context).inflate(R.layout.bmw_id8_music_item_layout, (ViewGroup) null);
        }
        init();
        return this.mMusicView;
    }

    private void init() {
        this.imgMusic = (ImageView) this.mMusicView.findViewById(R.id.imgMusic);
        this.imgBk = (ImageView) this.mMusicView.findViewById(R.id.imgBk);
        this.imgLine = this.mMusicView.findViewById(R.id.imgLine);
        this.mTitle = (TextView) this.mMusicView.findViewById(R.id.title);
        this.mTvArtist = (TextView) this.mMusicView.findViewById(R.id.musicArtist);
        this.mTvTitle = (TextView) this.mMusicView.findViewById(R.id.musicTitle);
        this.mProgress = (SlashThumbSeekView) this.mMusicView.findViewById(R.id.musicProgress);
        this.mImageIcon = (ImageView) this.mMusicView.findViewById(R.id.musicIcon);
        this.mImageEditIcon = (ImageView) this.mMusicView.findViewById(R.id.musicEditIcon);
        ImageButton imageButton = (ImageButton) this.mMusicView.findViewById(R.id.btnMusicPrev);
        this.btnMusicPrev = imageButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(this);
        }
        ImageButton imageButton2 = (ImageButton) this.mMusicView.findViewById(R.id.btnMusicNext);
        this.btnMusicNext = imageButton2;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(this);
        }
        ImageButton imageButton3 = (ImageButton) this.mMusicView.findViewById(R.id.btnMusicPlayPause);
        this.btnMusicPlayPause = imageButton3;
        if (imageButton3 != null) {
            imageButton3.setOnClickListener(this);
        }
        refreshPlayInfo();
        setMusicIcon();
        updateInfo();
    }

    public void updateInfo() {
        if (this.mTitle != null) {
            this.mTitle.setText(this.mContext.getString(R.string.lb_left_music).toUpperCase());
        }
        this.mMode = SysProviderOpt.getInstance(this.mContext).getRecordInteger("KESAIWEI_SYS_DISPLAY_MODE", 0);
        if (this.m_iModeSet == 20) {
            int i = this.mMode;
            int i2 = R.drawable.pemp_id8_main_edit_icon_music_btn_next_n;
            int i3 = R.drawable.pemp_id8_main_edit_icon_music_btn_prev_n;
            if (i == 0) {
                TextView textView = this.mTitle;
                if (textView != null) {
                    textView.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView = this.imgMusic;
                if (imageView != null) {
                    imageView.setImageResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_music_pic_b : R.drawable.pemp_id8_main_icon_music_b);
                }
                ImageView imageView2 = this.imgBk;
                if (imageView2 != null) {
                    imageView2.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_blue_small : R.drawable.pemp_bmw_id8_weather_item_background_blue);
                }
                View view = this.imgLine;
                if (view != null) {
                    view.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_b : R.drawable.pemp_id8_main_icon_weather_line_b);
                }
                SlashThumbSeekView slashThumbSeekView = this.mProgress;
                if (slashThumbSeekView != null) {
                    slashThumbSeekView.setProgressBimap(getBitmap(R.drawable.id8_main_icon_music_progress_bar_b_d));
                }
                ImageButton imageButton = this.btnMusicPrev;
                if (imageButton != null) {
                    if (!this.mSmallMode) {
                        i3 = R.drawable.pemp_id8_btn_music_prev_b;
                    }
                    imageButton.setBackgroundResource(i3);
                }
                ImageButton imageButton2 = this.btnMusicNext;
                if (imageButton2 != null) {
                    if (!this.mSmallMode) {
                        i2 = R.drawable.pemp_id8_btn_music_next_b;
                    }
                    imageButton2.setBackgroundResource(i2);
                }
            } else if (i == 1) {
                TextView textView2 = this.mTitle;
                if (textView2 != null) {
                    textView2.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView3 = this.imgMusic;
                if (imageView3 != null) {
                    imageView3.setImageResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_music_pic_r : R.drawable.pemp_id8_main_icon_music_r);
                }
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_red_small : R.drawable.pemp_bmw_id8_weather_item_background_red);
                }
                View view2 = this.imgLine;
                if (view2 != null) {
                    view2.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_r : R.drawable.pemp_id8_main_icon_weather_line_r);
                }
                SlashThumbSeekView slashThumbSeekView2 = this.mProgress;
                if (slashThumbSeekView2 != null) {
                    slashThumbSeekView2.setProgressBimap(getBitmap(R.drawable.id8_main_icon_music_progress_bar_r_d));
                }
                ImageButton imageButton3 = this.btnMusicPrev;
                if (imageButton3 != null) {
                    if (!this.mSmallMode) {
                        i3 = R.drawable.pemp_id8_btn_music_prev_r;
                    }
                    imageButton3.setBackgroundResource(i3);
                }
                ImageButton imageButton4 = this.btnMusicNext;
                if (imageButton4 != null) {
                    if (!this.mSmallMode) {
                        i2 = R.drawable.pemp_id8_btn_music_next_r;
                    }
                    imageButton4.setBackgroundResource(i2);
                }
            } else if (i == 2) {
                TextView textView3 = this.mTitle;
                if (textView3 != null) {
                    textView3.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView5 = this.imgMusic;
                if (imageView5 != null) {
                    imageView5.setImageResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_music_pic_y : R.drawable.pemp_id8_main_icon_music_y);
                }
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setImageResource(this.mSmallMode ? R.drawable.pemp_bmw_id8_weather_item_background_yellow_small : R.drawable.pemp_bmw_id8_weather_item_background_yellow);
                }
                View view3 = this.imgLine;
                if (view3 != null) {
                    view3.setBackgroundResource(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_line_y : R.drawable.pemp_id8_main_icon_weather_line_y);
                }
                SlashThumbSeekView slashThumbSeekView3 = this.mProgress;
                if (slashThumbSeekView3 != null) {
                    slashThumbSeekView3.setProgressBimap(getBitmap(R.drawable.id8_main_icon_music_progress_bar_y_d));
                }
                ImageButton imageButton5 = this.btnMusicPrev;
                if (imageButton5 != null) {
                    if (!this.mSmallMode) {
                        i3 = R.drawable.pemp_id8_btn_music_prev_y;
                    }
                    imageButton5.setBackgroundResource(i3);
                }
                ImageButton imageButton6 = this.btnMusicNext;
                if (imageButton6 != null) {
                    if (!this.mSmallMode) {
                        i2 = R.drawable.pemp_id8_btn_music_next_y;
                    }
                    imageButton6.setBackgroundResource(i2);
                }
            }
        } else {
            int i4 = this.mMode;
            if (i4 == 0) {
                TextView textView4 = this.mTitle;
                if (textView4 != null) {
                    textView4.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color));
                }
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_blue_small : R.drawable.bmw_id8_weather_item_background_blue);
                }
                View view4 = this.imgLine;
                if (view4 != null) {
                    view4.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_b : R.drawable.id8_main_icon_weather_line_b);
                }
                SlashThumbSeekView slashThumbSeekView4 = this.mProgress;
                if (slashThumbSeekView4 != null) {
                    slashThumbSeekView4.setProgressBimap(getBitmap(R.drawable.id8_main_icon_music_progress_bar_b_d));
                }
            } else if (i4 == 1) {
                TextView textView5 = this.mTitle;
                if (textView5 != null) {
                    textView5.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_sport));
                }
                ImageView imageView8 = this.imgBk;
                if (imageView8 != null) {
                    imageView8.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_red_small : R.drawable.bmw_id8_weather_item_background_red);
                }
                View view5 = this.imgLine;
                if (view5 != null) {
                    view5.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_r : R.drawable.id8_main_icon_weather_line_r);
                }
                SlashThumbSeekView slashThumbSeekView5 = this.mProgress;
                if (slashThumbSeekView5 != null) {
                    slashThumbSeekView5.setProgressBimap(getBitmap(R.drawable.id8_main_icon_music_progress_bar_r_d));
                }
            } else if (i4 == 2) {
                TextView textView6 = this.mTitle;
                if (textView6 != null) {
                    textView6.setTextColor(getColor(R.color.bmw_id8_mainpage_item_title_color_personal));
                }
                ImageView imageView9 = this.imgBk;
                if (imageView9 != null) {
                    imageView9.setImageResource(this.mSmallMode ? R.drawable.bmw_id8_weather_item_background_yellow_small : R.drawable.bmw_id8_weather_item_background_yellow);
                }
                View view6 = this.imgLine;
                if (view6 != null) {
                    view6.setBackgroundResource(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_line_y : R.drawable.id8_main_icon_weather_line_y);
                }
                SlashThumbSeekView slashThumbSeekView6 = this.mProgress;
                if (slashThumbSeekView6 != null) {
                    slashThumbSeekView6.setProgressBimap(getBitmap(R.drawable.id8_main_icon_music_progress_bar_y_d));
                }
            }
        }
        setMusicIcon();
    }

    public void updataFocusState(boolean z) {
        ImageView imageView;
        ImageView imageView2;
        if (!z) {
            ImageView imageView3 = this.imgBk;
            if (imageView3 != null) {
                imageView3.setForeground((Drawable) null);
            }
        } else if (this.m_iModeSet == 20) {
            int i = this.mMode;
            if (i == 0) {
                ImageView imageView4 = this.imgBk;
                if (imageView4 != null) {
                    imageView4.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_b_f : R.drawable.pemp_id8_main_icon_weather_b_f));
                }
            } else if (i == 1) {
                ImageView imageView5 = this.imgBk;
                if (imageView5 != null) {
                    imageView5.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_r_f : R.drawable.pemp_id8_main_icon_weather_r_f));
                }
            } else if (i == 2 && (imageView2 = this.imgBk) != null) {
                imageView2.setForeground(getDrawable(this.mSmallMode ? R.drawable.pemp_id8_main_edit_icon_weather_y_f : R.drawable.pemp_id8_main_icon_weather_y_f));
            }
        } else {
            int i2 = this.mMode;
            if (i2 == 0) {
                ImageView imageView6 = this.imgBk;
                if (imageView6 != null) {
                    imageView6.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_b_f : R.drawable.id8_main_icon_weather_b_f));
                }
            } else if (i2 == 1) {
                ImageView imageView7 = this.imgBk;
                if (imageView7 != null) {
                    imageView7.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_r_f : R.drawable.id8_main_icon_weather_r_f));
                }
            } else if (i2 == 2 && (imageView = this.imgBk) != null) {
                imageView.setForeground(getDrawable(this.mSmallMode ? R.drawable.id8_main_edit_icon_weather_y_f : R.drawable.id8_main_icon_weather_y_f));
            }
        }
    }

    public int[] getViewSize() {
        if (this.mSmallMode) {
            return new int[]{EventUtils.getPx(this.mContext, 326), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
        }
        return new int[]{EventUtils.getPx(this.mContext, 434), (int) this.mContext.getResources().getDimension(R.dimen.screenHeight)};
    }

    public void refreshPlayInfo() {
        LauncherModel instance = LauncherModel.getInstance();
        try {
            IEventService evtService = instance.getEvtService();
            int i = R.drawable.pemp_id8_btn_music_play_y;
            int i2 = R.drawable.pemp_id8_btn_music_play_r;
            int i3 = R.drawable.pemp_id8_btn_music_play_b;
            if (evtService == null || instance.getEvtService().getValidMode() != EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) {
                ImageButton imageButton = this.btnMusicPlayPause;
                if (imageButton != null) {
                    int i4 = this.mMode;
                    if (i4 == 0) {
                        if (this.mSmallMode) {
                            i3 = R.drawable.pemp_id8_main_edit_icon_music_btn_play_n;
                        }
                        imageButton.setBackgroundResource(i3);
                    } else if (i4 == 1) {
                        if (this.mSmallMode) {
                            i2 = R.drawable.pemp_id8_main_edit_icon_music_btn_play_n;
                        }
                        imageButton.setBackgroundResource(i2);
                    } else if (i4 == 2) {
                        if (this.mSmallMode) {
                            i = R.drawable.pemp_id8_main_edit_icon_music_btn_play_n;
                        }
                        imageButton.setBackgroundResource(i);
                    }
                }
            } else {
                String validModeTitleInfor = instance.getEvtService().getValidModeTitleInfor();
                String validModeArtistInfor = instance.getEvtService().getValidModeArtistInfor();
                instance.getEvtService().getValidModeAblumInfor();
                int validCurTime = instance.getEvtService().getValidCurTime();
                int validTotTime = instance.getEvtService().getValidTotTime();
                int validPlayStatus = instance.getEvtService().getValidPlayStatus();
                TextView textView = this.mTvArtist;
                if (textView != null) {
                    textView.setText(validModeArtistInfor);
                }
                TextView textView2 = this.mTvTitle;
                if (textView2 != null) {
                    textView2.setText(validModeTitleInfor);
                }
                SlashThumbSeekView slashThumbSeekView = this.mProgress;
                if (slashThumbSeekView != null) {
                    slashThumbSeekView.setMaxProgress(validTotTime);
                    this.mProgress.setCurProgress(validCurTime);
                }
                ImageButton imageButton2 = this.btnMusicPlayPause;
                if (imageButton2 == null) {
                    return;
                }
                if (validPlayStatus == 1) {
                    int i5 = this.mMode;
                    int i6 = R.drawable.pemp_id8_main_edit_icon_music_btn_pause_n;
                    if (i5 == 0) {
                        if (!this.mSmallMode) {
                            i6 = R.drawable.pemp_id8_btn_music_pause_b;
                        }
                        imageButton2.setBackgroundResource(i6);
                    } else if (i5 == 1) {
                        if (!this.mSmallMode) {
                            i6 = R.drawable.pemp_id8_btn_music_pause_r;
                        }
                        imageButton2.setBackgroundResource(i6);
                    } else if (i5 == 2) {
                        if (!this.mSmallMode) {
                            i6 = R.drawable.pemp_id8_btn_music_pause_y;
                        }
                        imageButton2.setBackgroundResource(i6);
                    }
                } else {
                    int i7 = this.mMode;
                    if (i7 == 0) {
                        if (this.mSmallMode) {
                            i3 = R.drawable.pemp_id8_main_edit_icon_music_btn_play_n;
                        }
                        imageButton2.setBackgroundResource(i3);
                    } else if (i7 == 1) {
                        if (this.mSmallMode) {
                            i2 = R.drawable.pemp_id8_main_edit_icon_music_btn_play_n;
                        }
                        imageButton2.setBackgroundResource(i2);
                    } else if (i7 == 2) {
                        if (this.mSmallMode) {
                            i = R.drawable.pemp_id8_main_edit_icon_music_btn_play_n;
                        }
                        imageButton2.setBackgroundResource(i);
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setIcon(Bitmap bitmap) {
        this.mIcon = bitmap;
        setMusicIcon();
    }

    private void setMusicIcon() {
        if (this.mImageIcon != null) {
            if (this.mIcon != null) {
                this.mImageIcon.setBackground(new BitmapDrawable(this.mContext.getResources(), this.mIcon));
            } else if (this.m_iModeSet == 20) {
                int i = this.mMode;
                if (i == 0) {
                    this.mImageIcon.setBackgroundResource(R.drawable.pemp_id8_main_icon_music_b2);
                } else if (i == 1) {
                    this.mImageIcon.setBackgroundResource(R.drawable.pemp_id8_main_icon_music_r2);
                } else if (i == 2) {
                    this.mImageIcon.setBackgroundResource(R.drawable.pemp_id8_main_icon_music_y2);
                }
            } else {
                int i2 = this.mMode;
                if (i2 == 0) {
                    this.mImageIcon.setBackgroundResource(R.drawable.id8_main_icon_music_b);
                } else if (i2 == 1) {
                    this.mImageIcon.setBackgroundResource(R.drawable.id8_main_icon_music_r);
                } else if (i2 == 2) {
                    this.mImageIcon.setBackgroundResource(R.drawable.id8_main_icon_music_y);
                }
            }
        }
        if (this.mImageEditIcon == null) {
            return;
        }
        if (this.mIcon != null) {
            this.mImageEditIcon.setBackground(new BitmapDrawable(this.mContext.getResources(), this.mIcon));
        } else if (this.m_iModeSet == 20) {
            int i3 = this.mMode;
            if (i3 == 0) {
                this.mImageEditIcon.setBackgroundResource(R.drawable.pemp_id8_main_edit_icon_music_pic_b2);
            } else if (i3 == 1) {
                this.mImageEditIcon.setBackgroundResource(R.drawable.pemp_id8_main_edit_icon_music_pic_r2);
            } else if (i3 == 2) {
                this.mImageEditIcon.setBackgroundResource(R.drawable.pemp_id8_main_edit_icon_music_pic_y2);
            }
        } else {
            int i4 = this.mMode;
            if (i4 == 0) {
                this.mImageEditIcon.setBackgroundResource(R.drawable.id8_main_edit_icon_music_pic_b);
            } else if (i4 == 1) {
                this.mImageEditIcon.setBackgroundResource(R.drawable.id8_main_edit_icon_music_pic_r);
            } else if (i4 == 2) {
                this.mImageEditIcon.setBackgroundResource(R.drawable.id8_main_edit_icon_music_pic_y);
            }
        }
    }

    private Drawable getDrawable(int i) {
        return this.mContext.getResources().getDrawable(i, (Resources.Theme) null);
    }

    private int getColor(int i) {
        return this.mContext.getResources().getColor(i, (Resources.Theme) null);
    }

    private Bitmap getBitmap(int i) {
        return BitmapFactory.decodeResource(this.mContext.getResources(), i);
    }

    public void onClick(View view) {
        try {
            if (LauncherModel.getInstance().getEvtService().getValidMode() != EventUtils.eSrcMode.SRC_MUSIC.getIntValue()) {
                return;
            }
            if (!this.mSmallMode) {
                int id = view.getId();
                if (id == R.id.btnMusicNext) {
                    Log.d(TAG, "onClick btnMusicNext");
                    playNext();
                } else if (id == R.id.btnMusicPlayPause) {
                    Log.d(TAG, "onClick btnMusicPlayPause");
                    playPause();
                } else if (id == R.id.btnMusicPrev) {
                    Log.d(TAG, "onClick btnMusicPrev");
                    playPrev();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void playPrev() {
        Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
        intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 3);
        this.mContext.sendBroadcast(intent);
    }

    private void playNext() {
        Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
        intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 2);
        this.mContext.sendBroadcast(intent);
    }

    private void playPause() {
        Intent intent = new Intent("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT");
        intent.putExtra("com.choiceway.eventcenter.EventUtils.ZXW_CAN_KEY_EVT_EXTRA", 6);
        this.mContext.sendBroadcast(intent);
    }
}
