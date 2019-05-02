package com.app.linio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Panels extends Fragment implements View.OnClickListener {

    Spinner filter;
    FloatingActionButton add;
    ListView panels_list;

    private FirebaseAuth auth;
    private DatabaseReference database;

    public Panels() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.panels, container, false);
        initSpinner(view);
        add = (FloatingActionButton)view.findViewById(R.id.add);
        add.setOnClickListener(this);
        panels_list = (ListView)view.findViewById(R.id.panels_list);
        List<Panel> panels = new ArrayList<>();
        return view;
    }

    private void initSpinner(View view) {
        filter = (Spinner)view.findViewById(R.id.filter);
        String[] filterData = {"Filter","Filter(Asc)","Filter(Desc)"};
        filter.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,filterData));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                DialogFragment dialogFragment = new AddPanel();
                dialogFragment.show(ft, "dialog");
                break;
        }
    }

}