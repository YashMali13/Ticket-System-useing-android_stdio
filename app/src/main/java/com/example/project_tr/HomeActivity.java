package com.example.project_tr;
import
        androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.NonNull;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
public class HomeActivity extends AppCompatActivity
{
    ////////////////// CODE FOR SIGN OUT TASK.........................................
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    //Not using//    TextView Name,Email; ..........NOT USING
    ////////////////// CODE FOR TAKING DATA TASK.........................................
    TextInputLayout name, source, destination, date, contact;
    FloatingActionButton fb;
    Button sbmt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ////////////////// CODE FOR SIGN OUT TASK.........................................
//Not using//       Name = findViewById(R.id.name);
//Not using//        //Name = findViewById(R.id.Name);
//Not using//       Email = findViewById(R.id.email);
//Not using//       //  Email = findViewById(R.id.Email);


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null)
        {
//Not using//            String personName = acct.getDisplayName();
//Not using//            String personEmail = acct.getEmail();
//Not using//            Name.setText(personName);
//Not using//            Email.setText(personEmail);
        }



        ////////////////// CODE FOR TAKING DATA TASK.........................................
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        name = (TextInputLayout) findViewById(R.id.nametext);
        source = (TextInputLayout) findViewById(R.id.sourcetext);
        destination = (TextInputLayout) findViewById(R.id.destinationtext);
        date = (TextInputLayout) findViewById(R.id.datetext);
        contact = (TextInputLayout) findViewById(R.id.emailtext);
        fb = (FloatingActionButton) findViewById(R.id.fbtn);
        sbmt = (Button) findViewById(R.id.sbmt_add);


        sbmt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // for checking if any field is empty...
                if (name.getEditText().getText().toString().equals("")|| source.getEditText().getText().toString().equals("")|| destination.getEditText().getText().toString().equals("") || date.getEditText().getText().toString().equals("")|| contact.getEditText().getText().toString().equals("") )
                {
                    Toast.makeText(HomeActivity.this, "Please enter all the details.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // for checking source stations...........................................................................................................................
                    if
                    (
                                    source.getEditText().getText().toString().equals("Airoli")||
                                    source.getEditText().getText().toString().equals("Thane")||
                                    source.getEditText().getText().toString().equals("Kurla")||
                                    source.getEditText().getText().toString().equals("CSMT")||
                                    source.getEditText().getText().toString().equals("vashi")||
                                    source.getEditText().getText().toString().equals("Panvel")||
                                    source.getEditText().getText().toString().equals("Bhagalpur")
//                                    source.getEditText().getText().toString().equals("Bhagwanpur")||

                    )
                    {
                        // for checking destination stations..............................................................................................................
                        if
                        (
                                        destination.getEditText().getText().toString().equals("Airoli")||
                                        destination.getEditText().getText().toString().equals("Thane")||
                                        destination.getEditText().getText().toString().equals("Nagpur")||
                                        destination.getEditText().getText().toString().equals("CSMT")||
                                        destination.getEditText().getText().toString().equals("Panvel")
//                                            destination.getEditText().getText().toString().equals("Bhalki")||

                        )
                        {
                            //                          if (source.getEditText().getText().toString() == destination.getEditText().getText().toString() ) // wrong syntax
                            //                           if (source == destination ) // wrong syntax
                            if(source.getEditText().getText().toString().equals(destination.getEditText().getText().toString()) ) // for checking both source & destination station should be different...
                            {
                                Toast.makeText(HomeActivity.this, "Source & Destination could not be same.", Toast.LENGTH_SHORT).show();
                            }
                            else // IF ALL CORRECT THEN DATA WILL UPLOAD HERE..................................................................................................

                            {
                                processinsert(name.getEditText().getText().toString(), source.getEditText().getText().toString(), destination.getEditText().getText().toString(), date.getEditText().getText().toString(), contact.getEditText().getText().toString());
                            }
                        }
                        else // if destination station is wrong...
                        {
                            Toast.makeText(HomeActivity.this, "Enter the correct destination station.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else // if source station is wrong...
                    {
                        Toast.makeText(HomeActivity.this, "Enter the correct source station.", Toast.LENGTH_SHORT).show();
                    }// inner else...if source station is right.
                } // main else... if no any single field is empty.

            }
        }                      );


        fb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(),fetchdata.class));
            }
        }                    );

    }


    ////////////////// CODE FOR TAKING DATA TASK.........................................
    private void processinsert(String n, String s, String de, String da, String e)
    {
        String res=new dbmanager(this).addrecord(n,s,de,da,e);
        name.getEditText().setText("");
        source.getEditText().setText("");
        destination.getEditText().setText("");
        date.getEditText().setText("");
        contact.getEditText().setText("");
        Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
    }


}