package com.app.linio;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPanel extends DialogFragment implements View.OnClickListener {
    EditText panelTitle;
    Button cancel;
    Button create;

    private DatabaseReference database;
    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_panel, container, false);
        panelTitle = (EditText) view.findViewById(R.id.panel_title);
        cancel = (Button) view.findViewById(R.id.cancel);
        create = (Button) view.findViewById(R.id.create);
        cancel.setOnClickListener(this);
        create.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                break;
            case R.id.create:
                createPanel(panelTitle.getText().toString());
                break;
        }
    }

    private void createPanel(String title) {
        auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        if (uid != null && title != null) {
            database = FirebaseDatabase.getInstance().getReference();
            database.child(uid).child("panels").child(title).child("title").setValue(title);
        } else if (uid == null) Toast.makeText(getContext(), "No UID!", Toast.LENGTH_LONG).show();
        else Toast.makeText(getContext(), "No panel title specified!", Toast.LENGTH_LONG).show();
    }

}