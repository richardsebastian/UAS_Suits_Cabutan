package umn.ac.id.uas_suits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword;
    Button mRegister;
    TextView mLoginBtt;
    String email,password;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mRegister = findViewById(R.id.register);
        mLoginBtt = findViewById(R.id.hvacc);
        fAuth = FirebaseAuth.getInstance();

        mLoginBtt = (TextView)findViewById(R.id.hvacc);
        mLoginBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        fAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Register.this, "Register Succesfull", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this,Login.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}