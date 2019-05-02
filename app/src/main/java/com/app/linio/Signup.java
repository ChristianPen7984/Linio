package com.app.linio;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends Fragment implements View.OnClickListener {

    private EditText in_email, in_password, in_re_password;
    private Button signup;
    private FirebaseAuth auth;
    private DatabaseReference database;

    public Signup() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);

        auth = FirebaseAuth.getInstance();

        in_email = (EditText)view.findViewById(R.id.email);
        in_password = (EditText)view.findViewById(R.id.password);
        in_re_password = (EditText)view.findViewById(R.id.repeat_password);
        signup = (Button)view.findViewById(R.id.signup);
        signup.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        final String email = in_email.getText().toString();
        String password = in_password.getText().toString();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getContext(), "Auth Failed", Toast.LENGTH_LONG).show();

                        } else {
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.fragmentContainer, new Home());
                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            ft.addToBackStack(null);
                            ft.commit();
                            String uid = auth.getUid();
                            writeNewUser(uid,email);
                        }
                    }
                });
    }

    private void writeNewUser(String uid, String email) {
        User user = new User(email);
        database = FirebaseDatabase.getInstance().getReference();
        database.child(uid).setValue(user);
    }

}
