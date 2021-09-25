package com.reader.pdfreader2021;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.reader.pdfreader2021.AppsAdapter;
import com.reader.pdfreader2021.Util;

public class BackActivity extends Activity {
    private CardView cardView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_back);
        this.cardView = (CardView) findViewById(R.id.exitlayout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.applist);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.hasFixedSize();
        if (Util.getInstance().getAppData() != null && Util.getInstance().getAppData().size() > 0) {
            recyclerView.setAdapter(new AppsAdapter(this, Util.getInstance().getAppData()));
        }
        findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.BackActivity.AnonymousClass1 */

            public void onClick(View view) {
                BackActivity.this.startActivity(new Intent(BackActivity.this, ThanksActivity.class));
                BackActivity.this.finish();
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.BackActivity.AnonymousClass2 */

            public void onClick(View view) {
                BackActivity.this.startActivity(new Intent(BackActivity.this, StartActivity.class));
                BackActivity.this.finish();
            }
        });
    }

    public void onBackPressed() {
        if (this.cardView.getVisibility() == 4) {
            this.cardView.setVisibility(0);
            this.cardView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), 17432576));
        }
    }
}
