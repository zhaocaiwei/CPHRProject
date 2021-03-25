package com.polygon.cphrporject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button logInBtn;
    Button forgotPasswordBtn;
//    Button createNewAccountBtn;
    Button changeEmailAddressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInBtn = findViewById(R.id.log_in_btn);
        forgotPasswordBtn = findViewById(R.id.forgot_password_btn);
//        createNewAccountBtn = findViewById(R.id.create_new_account_btn);
        changeEmailAddressBtn = findViewById(R.id.change_email_address_btn);

        logInBtn.setOnClickListener(this);
        forgotPasswordBtn.setOnClickListener(this);
//        createNewAccountBtn.setOnClickListener(this);
        changeEmailAddressBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.log_in_btn:
                    Intent li = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(li);
                    break;
                case R.id.forgot_password_btn:
                    Intent fp = new Intent(getApplicationContext(), ForgotPassword.class);
                    startActivity(fp);
                    break;
                case R.id.change_email_address_btn:
                    Intent cea = new Intent(getApplicationContext(), ChangeEmailAddress.class);
                    startActivity(cea);
                    break;
//                case R.id.create_new_account_btn:
//                    Intent cna = new Intent(getApplicationContext(), CreateNewAccount.class);
//                    startActivity(cna);
//                    break;
                default:
                    throw new IllegalStateException("Unexpected Valid: " + view.getId());
            }
    }
}
