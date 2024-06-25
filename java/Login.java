package com.example.rinvizle.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button bLogin;
    private EditText etUsername, etPassword;
    private TextView tvRegisterLink;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        if(session.loggedin()){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:
                login();
                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(Login.this, Register.class));
                finish();
                break;
            default:
        }
    }

    private void login(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (db.getUser(username,password)){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong Username and Password",Toast.LENGTH_SHORT).show();
        }
    }
}