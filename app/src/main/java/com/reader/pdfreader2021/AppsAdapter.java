package com.reader.pdfreader2021;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {
    private Activity activity;
    public ArrayList<AppData> mData;
    private LayoutInflater mInflater;

    public AppsAdapter(Activity activity2, ArrayList<AppData> arrayList) {
        this.activity = activity2;
        this.mInflater = LayoutInflater.from(activity2);
        this.mData = arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.app_row, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AppData appData = mData.get(i);
        viewHolder.name.setText(appData.getName());
        Glide.with(this.activity).load(appData.getLogo()).into(viewHolder.logo);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            /* class com.reader.pdfreader2021.apps.AppsAdapter.AnonymousClass1 */

            public void onClick(View view) {
                Util.gotoPlayStore(AppsAdapter.this.activity, appData.getPakage());
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    /* access modifiers changed from: package-private */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView name;

        ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.appname);
            this.logo = (ImageView) view.findViewById(R.id.logo);
        }
    }
}
