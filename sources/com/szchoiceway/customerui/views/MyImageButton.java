package com.szchoiceway.customerui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.szchoiceway.customerui.R;
import com.szchoiceway.customerui.utils.SysProviderOpt;

public class MyImageButton extends ImageButton {
    private static final String TAG = "MyRadioButton";
    private Context mContext;
    private int theme1;
    private int theme2;
    protected int themeNum;
    private TypedArray typedArray;

    public MyImageButton(Context context) {
        super(context);
        this.theme1 = R.drawable.ksw_theme1_music;
        this.theme2 = R.drawable.ksw_theme2_music;
        this.themeNum = 1;
    }

    public MyImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.theme1 = R.drawable.ksw_theme1_music;
        this.theme2 = R.drawable.ksw_theme2_music;
        this.themeNum = 1;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MyImageButton);
        this.typedArray = obtainStyledAttributes;
        this.theme1 = obtainStyledAttributes.getResourceId(0, this.theme1);
        this.theme2 = this.typedArray.getResourceId(1, this.theme2);
        init();
    }

    public void init() {
        int recordInteger = SysProviderOpt.getInstance(this.mContext).getRecordInteger(SysProviderOpt.KSW_BENZ_THEME_INDEX, this.themeNum);
        this.themeNum = recordInteger;
        if (recordInteger == 1) {
            setBackground(this.mContext.getResources().getDrawable(this.theme1));
        } else {
            setBackground(this.mContext.getResources().getDrawable(this.theme2));
        }
    }

    public void setTheme(int[] iArr) {
        if (iArr.length == 2) {
            this.theme1 = iArr[0];
            this.theme2 = iArr[1];
        }
    }
}
