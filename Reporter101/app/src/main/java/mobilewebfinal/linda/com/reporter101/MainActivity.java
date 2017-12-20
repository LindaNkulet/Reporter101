package mobilewebfinal.linda.com.reporter101;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button signup;
    private EditText name;
    private  EditText email;
    private EditText password;
    private EditText phoneNumber;
    private EditText age;
    private Spinner organizations;
    private TextView signin;

    String username;
    String useremail;
    String userpwd;
    String userphoneno;
    String userage;
    String organization;

    private ProgressDialog progressDialog;
    String finalResult ;
    String HttpURL = "https://linda-delishoi.000webhostapp.com/mobilewebfinal/userRegistration.php";
    Boolean CheckEditText ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //progressDialog=new ProgressDialog(this);

        name=(EditText)findViewById(R.id.EditTextName);
        email=(EditText)findViewById(R.id.EditTextEmail);
        password=(EditText)findViewById(R.id.EditTextPassword);
        phoneNumber=(EditText)findViewById(R.id.EditTextPhoneNo);
        age=(EditText)findViewById(R.id.EditTextAge);
        signin=(TextView)findViewById(R.id.textViewsignin);
        signup=(Button)findViewById(R.id.buttonSignup);
        organizations=(Spinner)findViewById(R.id.spinnerOrganization);

        //signup.setOnClickListener(this);
        //signin.setOnClickListener(this);

        //Adding Click Listener on button.
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(username,useremail,userpwd,userphoneno,userage,organization);


                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

    }



    public void CheckEditTextIsEmptyOrNot(){
        username=name.getText().toString().trim();
        useremail=email.getText().toString().trim();
         userpwd=email.getText().toString().trim();
         userphoneno=email.getText().toString().trim();
         userage=email.getText().toString().trim();
         organization=organizations.getSelectedItem().toString();

        if(TextUtils.isEmpty(username)){
            //username empty
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            //stop the function from executing further

            CheckEditText = false;
        }

         if(TextUtils.isEmpty(useremail)){
            //username empty
            Toast.makeText(this,"Please enter your email",Toast.LENGTH_LONG).show();
            //stop the function from executing further
            CheckEditText = false;
        }

         if(TextUtils.isEmpty(userpwd)){
            //username empty
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_LONG).show();
            //stop the function from executing further
            CheckEditText = false;

        }

        if(TextUtils.isEmpty(userphoneno)){
            //username empty
            Toast.makeText(this,"Please enter your phone number",Toast.LENGTH_LONG).show();
            //stop the function from executing further
            CheckEditText = false;
        }

         if(TextUtils.isEmpty(userage)){
            //username empty
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_LONG).show();
            //stop the function from executing further
            CheckEditText = false;
        }

         if(TextUtils.isEmpty(organization)){
            //username empty
            Toast.makeText(this,"Please select an organization",Toast.LENGTH_LONG).show();
            //stop the function from executing further
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String name, final String email, final String password, final String phonenumber,final String age, final String organization){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(MainActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();


            }

            @Override
            protected String doInBackground(String... params) {

                String link = "https://linda-delishoi.000webhostapp.com/mobilewebfinal/userRegistration.php?username="+
                        params[0]+"&email="+params[1]+"&password="+params[2]+"&phonenumber="+params[3]+
                        "&age="+params[4]+"&organization="+params[5];
                //finalResult = httpParse.postRequest(hashMap, HttpURL);
                try {
                    URL url = new URL(link);

                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI(link));

                    //get the response from server
                    HttpResponse response = client.execute(request);
                    BufferedReader in = new BufferedReader
                            (new InputStreamReader(response.getEntity().getContent()));

                    return in.readLine();
                } catch (IOException | URISyntaxException e) {
                    return e.getCause() + " | " + e.getMessage();
                }
                //return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(name,email,password,phonenumber,age,organization);
    }

    @Override
    public void onClick(View view){
    if(view == signup){
       //registerUser();
    }
    if(view ==signin){
       //open login activity

    }
    }
}
