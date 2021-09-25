package com.reader.pdfreader2021;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class SplashActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            /* class com.reader.pdfreader2021.SplashActivity.AnonymousClass1 */

            public void run() {
                SplashActivity.this.checkAndroidVersion();
            }
        }, 2000);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
            return;
        }
        startActivity(new Intent(this, StartActivity.class));
        finish();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CAMERA") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            startActivity(new Intent(this, StartActivity.class));
            finish();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermission();
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestPermission();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1432) {
            return;
        }
        if (iArr.length > 0) {
            boolean z = false;
            boolean z2 = iArr[0] == 0;
            boolean z3 = iArr[1] == 0;
            if (iArr[2] == 0) {
                z = true;
            }
            if (z3 && z2 && z) {
                startActivity(new Intent(this, StartActivity.class));
                finish();
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestPermission();
        }
    }

    /* access modifiers changed from: package-private */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermission() {
        requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1432);
    }
}
