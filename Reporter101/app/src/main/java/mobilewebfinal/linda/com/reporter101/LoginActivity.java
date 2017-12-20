package mobilewebfinal.linda.com.reporter101;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private EditText emailtxt;
    private EditText passwordtxt;
    private Button loginbtn;
    String PasswordHolder, EmailHolder;

    String finalResult;
    String HttpURL = "https://linda-delishoi.000webhostapp.com/mobilewebfinal/userLogin.php";
    Boolean CheckEditText;
    ProgressDialog progressDialog;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailtxt = (EditText) findViewById(R.id.EditTextEmail);
        passwordtxt = (EditText) findViewById(R.id.EditTextPwd);
        loginbtn = (Button) findViewById(R.id.buttonLogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {

                    Toast.makeText(LoginActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void CheckEditTextIsEmptyOrNot() {

        EmailHolder = emailtxt.getText().toString().trim();
        PasswordHolder = passwordtxt.getText().toString().trim();

        if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
            CheckEditText = false;
        } else {

            CheckEditText = true;
        }
    }

    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(LoginActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    finish();

                    Intent intent = new Intent(LoginActivity.this, PostActivity.class);

                    //intent.putExtra(UserEmail,email);

                    startActivity(intent);

                }
                else{

                    Toast.makeText(LoginActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                //hashMap.put("email",params[0]);

                //hashMap.put("password",params[1]);

                //finalResult = httpParse.postRequest(hashMap, HttpURL);

                //return finalResult;
                String link = "https://linda-delishoi.000webhostapp.com/mobilewebfinal/userLogin.php?email="+
                        params[0]+"&password="+params[1];

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
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }
}