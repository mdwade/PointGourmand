package com.pg.sn.point_gourmand;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {

    private static final int RC_SIGN_IN = 123;
    private Button btn ;

    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.activity_sign_in, container, false);
        btn =(Button) rootView.findViewById(R.id.deconnex);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deconnexion();
                newProf(view);

            }
        });
        return rootView;
    }

    public void newProf(View view) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    private void deconnexion () {
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

}
