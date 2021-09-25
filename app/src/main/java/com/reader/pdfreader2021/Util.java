package com.reader.pdfreader2021;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

public class Util {
    public static String BASE_URL = "http://androidmasterr.com/server_ad/gridapps.php?package=com.coddroid.pubgsound";
    private static Util instance;
    private ArrayList<AppData> appData = new ArrayList<>();

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public ArrayList<AppData> getAppData() {
        return this.appData;
    }

    public void setAppData(ArrayList<AppData> arrayList) {
        this.appData = arrayList;
    }

    public static void gotoPlayStore(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        try {
            intent.setData(Uri.parse("market://details?id=" + str));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + str));
            activity.startActivity(intent);
        }
    }

    public ArrayList<AppData> getCategoryOne() {
        ArrayList<AppData> arrayList = new ArrayList<>();
        if (getAppData() != null) {
            for (int i = 0; i < getAppData().size(); i++) {
                if (getAppData().get(i).getCategory() == 1) {
                    AppData appData2 = new AppData();
                    appData2.setId(getAppData().get(i).getId());
                    appData2.setName(getAppData().get(i).getName());
                    appData2.setPakage(getAppData().get(i).getPakage());
                    appData2.setLogo(getAppData().get(i).getLogo());
                    arrayList.add(appData2);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<AppData> getCategoryTwo() {
        ArrayList<AppData> arrayList = new ArrayList<>();
        if (getAppData() != null) {
            for (int i = 0; i < getAppData().size(); i++) {
                if (getAppData().get(i).getCategory() == 2) {
                    AppData appData2 = new AppData();
                    appData2.setId(getAppData().get(i).getId());
                    appData2.setName(getAppData().get(i).getName());
                    appData2.setPakage(getAppData().get(i).getPakage());
                    appData2.setLogo(getAppData().get(i).getLogo());
                    arrayList.add(appData2);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<AppData> getCategoryThree() {
        ArrayList<AppData> arrayList = new ArrayList<>();
        if (getAppData() != null) {
            for (int i = 0; i < getAppData().size(); i++) {
                if (getAppData().get(i).getCategory() == 3) {
                    AppData appData2 = new AppData();
                    appData2.setId(getAppData().get(i).getId());
                    appData2.setName(getAppData().get(i).getName());
                    appData2.setPakage(getAppData().get(i).getPakage());
                    appData2.setLogo(getAppData().get(i).getLogo());
                    arrayList.add(appData2);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<AppData> getCategoryFour() {
        ArrayList<AppData> arrayList = new ArrayList<>();
        if (getAppData() != null) {
            for (int i = 0; i < getAppData().size(); i++) {
                if (getAppData().get(i).getCategory() == 4) {
                    AppData appData2 = new AppData();
                    appData2.setId(getAppData().get(i).getId());
                    appData2.setName(getAppData().get(i).getName());
                    appData2.setPakage(getAppData().get(i).getPakage());
                    appData2.setLogo(getAppData().get(i).getLogo());
                    arrayList.add(appData2);
                }
            }
        }
        return arrayList;
    }
}
