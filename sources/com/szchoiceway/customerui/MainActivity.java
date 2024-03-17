package com.szchoiceway.customerui;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.szchoiceway.customerui.utils.MultipleUtils;

public class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.d("CustomerUI MainActivity", "oNCreate");
        super.onCreate(bundle);
        MultipleUtils.setCustomDensity(this, getApplication().getResources(), 0);
        setContentView((int) R.layout.activity_main);
    }
}
