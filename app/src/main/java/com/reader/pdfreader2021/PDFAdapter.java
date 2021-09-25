package com.reader.pdfreader2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class PDFAdapter extends ArrayAdapter<File> {
    private ArrayList<File> allPDF;

    public static class ViewHolder {
        private TextView tvFilename;
    }

    public int getItemViewType(int i) {
        return i;
    }

    public PDFAdapter(Context context, ArrayList<File> arrayList) {
        super(context, (int) R.layout.item_pdf, arrayList);
        this.allPDF = arrayList;
    }

    public int getViewTypeCount() {
        if (this.allPDF.size() > 0) {
            return this.allPDF.size();
        }
        return 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_pdf, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvFilename = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvFilename.setText(this.allPDF.get(i).getName());
        return view;
    }
}
