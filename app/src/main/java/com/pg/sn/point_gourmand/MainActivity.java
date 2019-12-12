package com.pg.sn.point_gourmand;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn ;
    private DatabaseReference mDatabase;
    TextView slogan;
    private TextView titre ;
    private static final int RC_SIGN_IN = 123;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();




        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        titre = (TextView) findViewById(R.id.titre);
        slogan = (TextView) findViewById(R.id.slogan);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
        slogan.setTypeface(font);

        updateUIWhenCreating();

        FloatingActionButton cart = (FloatingActionButton) findViewById(R.id.fab);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ShowCommande.class);
                startActivity(i);
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (user == null) {
                    startSignInActivity();
                }
                else {
                    newProf(v);
                }
            }
        });
    }

    public void newProf(View view) {
        Intent intent = new Intent(this, SignIn1.class);
        startActivity(intent);
    }



    @Nullable
    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser(); }

    protected Boolean isCurrentUserLogged(){ return (this.getCurrentUser() != null); }

    private void updateUIWhenCreating(){

        if (this.getCurrentUser() != null){

            //Get picture URL from Firebase
            if (this.getCurrentUser().getPhotoUrl() != null) {
                Glide.with(this)
                        .load(this.getCurrentUser().getPhotoUrl())
                        .apply(RequestOptions.circleCropTransform())
                        ;
            }

            //Get email & username from Firebase


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email = TextUtils.isEmpty(user.getEmail()) ? getString(R.string.info_no_email_found) : this.getCurrentUser().getEmail();
                String username = TextUtils.isEmpty(user.getDisplayName()) ? getString(R.string.info_no_username_found) : this.getCurrentUser().getDisplayName();
                String password = user.getUid();
                writeNewUser(password,username,email);

                btnSignIn.setText("Enter");
                titre.setText("Hello "+username);
                slogan.setText("Point Gourmand vous souhaite la Bienvenue ");
                slogan.setTextSize(45);
                btnSignIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newProf(view);
                    }
                });
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }


    private void writeNewUser(String password, String name, String email) {
        User user = new User(name, email,password);

        mDatabase.child("Users").child(name).setValue(user);
    }

    private void startSignInActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(), //EMAIL
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build())) // SUPPORT GOOGLE
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.log)
                        .build(),
                RC_SIGN_IN);
    }
}
