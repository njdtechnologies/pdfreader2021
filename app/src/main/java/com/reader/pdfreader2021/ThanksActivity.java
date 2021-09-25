package com.reader.pdfreader2021;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ThanksActivity extends Activity {
    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_thanks);

        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.ThanksActivity.AnonymousClass1 */

            public void onClick(View view) {
                ThanksActivity.this.finishAffinity();
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.ThanksActivity.AnonymousClass2 */

            public void onClick(View view) {
                ThanksActivity.this.startActivity(new Intent(ThanksActivity.this, StartActivity.class));
            }
        });
        findViewById(R.id.rateapp).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.ThanksActivity.AnonymousClass3 */

            public void onClick(View view) {
                try {
                    ThanksActivity thanksActivity = ThanksActivity.this;
                    thanksActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + ThanksActivity.this.getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(ThanksActivity.this, "You don't have Google Play installed", 0).show();
                }
            }
        });
    }
}
