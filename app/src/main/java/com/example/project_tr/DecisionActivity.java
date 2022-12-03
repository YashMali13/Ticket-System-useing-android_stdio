package com.example.project_tr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.view.WindowManager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;


public class DecisionActivity extends AppCompatActivity
{

    TextView welcome_headline, Find_Your_Dream_Yatras_Here_headline, Is_Your_Tickets_Going_Waste_headline, Planning_For_Yatra_headline;
    Button btn_sell_option, btn_buy_option;
    // Button signOutBtn;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signOutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);


        welcome_headline= (TextView) findViewById(R.id.welcome_headline);
        Find_Your_Dream_Yatras_Here_headline = (TextView) findViewById(R.id.Find_Your_Dream_Yatras_Here_headline);
        Is_Your_Tickets_Going_Waste_headline = (TextView) findViewById(R.id.Is_Your_Tickets_Going_Waste_headline);
        Planning_For_Yatra_headline = (TextView) findViewById(R.id.Planning_For_Yatra_headline);
        btn_sell_option = (Button) findViewById(R.id.btn_sell_option);
        btn_buy_option = (Button) findViewById(R.id.btn_buy_option);


        signOutBtn = findViewById(R.id.signout);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);



        ////////////////// CODE FOR Moving To Selling Page.........................................

        btn_sell_option.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        }                             );

//        public void setBtn_sell_option(@NonNull Button btn_sell_option)
//        {
//            this.btn_sell_option = btn_sell_option;
//            btn_sell_option.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View view)
//                {
//                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                }
//            }                                 );
//        }


        ////////////////// CODE FOR Moving To Offers' Page.........................................
        btn_buy_option.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
            }
        }                        );

//        public void setBtn_buy_option (@NonNull Button btn_buy_option)
//        {
//            this.btn_buy_option = btn_buy_option;
//            btn_buy_option.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View view)
//                {
//                    startActivity(new Intent(getApplicationContext(), fetchdata.class));
//                }
//            }                                );
//        }


        ////////////////// CODE FOR SIGN OUT TASK.........................................
        signOutBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    void signOut()
    {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>()
                                            {
                                                @Override
                                                public void onComplete(Task<Void> task) {
                                                    finish();
                                                    startActivity(new Intent(DecisionActivity.this,MainActivity.class));
                                                }
                                            }
        );
    }
}

