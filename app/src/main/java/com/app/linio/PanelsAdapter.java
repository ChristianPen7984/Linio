package com.app.linio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PanelsAdapter extends BaseAdapter {

    Context context;
    List<Panel> panels;

    public PanelsAdapter() { }

    public PanelsAdapter(Context context, List<Panel> panels) {
        this.context = context;
        this.panels = panels;
    }

    @Override
    public int getCount() {
        return panels.size();
    }

    @Override
    public Object getItem(int position) {
        return panels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.panels_list_sample,null);
        TextView title = (TextView)view.findViewById(R.id.panel_title);
        title.setText(panels.get(position).getTitle());
        return view;
    }
}
