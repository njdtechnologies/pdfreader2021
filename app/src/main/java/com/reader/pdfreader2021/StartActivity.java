package com.reader.pdfreader2021;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.reader.pdfreader2021.AppData;
import com.reader.pdfreader2021.MoreAppActivity;
import com.reader.pdfreader2021.Util;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StartActivity extends Activity {


    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_start);
        AndroidNetworking.initialize(this);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.StartActivity.AnonymousClass1 */

            public void onClick(View view) {
                StartActivity.this.startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });
        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.StartActivity.AnonymousClass2 */

            public void onClick(View view) {
                try {
                    StartActivity startActivity = StartActivity.this;
                    startActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + StartActivity.this.getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(StartActivity.this, "You don't have Google Play Installed", 0).show();
                }
            }
        });
        findViewById(R.id.more).setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.StartActivity.AnonymousClass3 */

            public void onClick(View view) {
                StartActivity.this.startActivity(new Intent(StartActivity.this, MoreAppActivity.class));
            }
        });
        AndroidNetworking.post(Util.BASE_URL).setTag((Object) getPackageName()).setPriority(Priority.HIGH).build().getAsJSONArray(new JSONArrayRequestListener() {
            /* class com.reader.pdfreader2021.StartActivity.AnonymousClass4 */

            @Override // com.androidnetworking.interfaces.JSONArrayRequestListener
            public void onResponse(JSONArray jSONArray) {
                try {
                    ArrayList<AppData> arrayList = new ArrayList<>();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        AppData appData = new AppData();
                        appData.setId(jSONObject.getInt("id"));
                        appData.setName(jSONObject.getString("name"));
                        appData.setPakage(jSONObject.getString("pakage"));
                        appData.setLogo(jSONObject.getString("logo"));
                        appData.setCategory(jSONObject.getInt("cat"));
                        arrayList.add(appData);
                    }
                    Util.getInstance().setAppData(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.androidnetworking.interfaces.JSONArrayRequestListener
            public void onError(ANError aNError) {
                Log.e("Error", aNError.toString());
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(this, BackActivity.class));
    }
}
