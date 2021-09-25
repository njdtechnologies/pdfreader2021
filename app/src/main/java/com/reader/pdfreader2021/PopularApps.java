package com.reader.pdfreader2021;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PopularApps extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_apps, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.applist);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.hasFixedSize();
        if (Util.getInstance().getCategoryThree().size() > 0) {
            recyclerView.setAdapter(new AppsAdapter(getActivity(), Util.getInstance().getCategoryThree()));
        }
        return inflate;
    }
}
