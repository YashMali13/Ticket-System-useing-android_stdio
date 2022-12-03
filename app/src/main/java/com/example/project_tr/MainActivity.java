package com.example.project_tr;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity
{
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;


    /// Defining button(Google button) for login
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        /// Initialing button(Google button) for login
        googleBtn = findViewById(R.id.google_btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

///////////////////////////////////////////////////////////// SIGN UP BY REGISTERING (IN APP : DATABASE).............................
        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter all the details.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false)
                        {
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true)
                            {
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(),DecisionActivity.class);
                                navigateToSecondActivity();
//                                startActivity(intent);
                            }else
                            {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

///////////////////////////////////////////////////////////// MOVING TO SIGN IN PAGE SETTING.............................
        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

///////////////////////////////////////////////////////////// SIGN UP BY GOOGLE.............................

        /// Google login : process 1
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null)
        {
            // navigateToSecondActivity();.............................. ????????????????????????????????????????????????????/INSTEAD OF THIS,BELOW ONE IS NOT WORK
            //navigateToHomeActivity();//.......................NOT WORK
            Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
//            Intent intent  = new Intent(getApplicationContext(), DecisionActivity.class);
            navigateToSecondActivity();
//            startActivity(intent);
        }
        /// Google login : process 2
        googleBtn.setOnClickListener(new View.OnClickListener()
                                     {
                                         @Override
                                         public void onClick(View v){
                                             signIn();
                                         }
                                     }
        );


    }


    /// Google login : process 3
    void signIn()
    {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    /// Google login : process 4
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try
            {
                task.getResult(ApiException.class);
                // navigateToSecondActivity();.............................. ????????????????????????????????????????????????????/INSTEAD OF THIS,BELOW ONE IS NOT WORK
                //navigateUpToHomeActivity();//.......................NOT WORK
                Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
//                  Intent intent  = new Intent(getApplicationContext(), DecisionActivity.class);
//                  startActivity(intent);
                navigateToSecondActivity();
            } catch (ApiException e)
            {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }

    ////// Call to Decision Activity
    void navigateToSecondActivity()
    {
        finish();
        Intent intent = new Intent(MainActivity.this,DecisionActivity.class);
        startActivity(intent);
    }



}
