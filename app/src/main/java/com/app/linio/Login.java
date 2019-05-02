package com.app.linio;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends Fragment implements View.OnClickListener {

    EditText in_email;
    EditText in_password;
    Button login;
    FirebaseAuth auth;

    public Login() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.login, container, false);
       auth = FirebaseAuth.getInstance();
       if (auth.getCurrentUser() != null) {
           Toast.makeText(getContext(),auth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();
       }
       in_email = view.findViewById(R.id.email);
       in_password = view.findViewById(R.id.password);
       login = view.findViewById(R.id.login);
       login.setOnClickListener(this);
       return view;
    }

    @Override
    public void onClick(final View v) {
        String email = in_email.getText().toString();
        final String password = in_password.getText().toString();

        switch (v.getId()) {
            case R.id.login:
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Auth Failed", Toast.LENGTH_LONG).show();

                                } else {
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.fragmentContainer, new Panels());
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    Toast.makeText(getContext(),auth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
            case R.id.create_account:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer, new Signup());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
        }
    }

}
