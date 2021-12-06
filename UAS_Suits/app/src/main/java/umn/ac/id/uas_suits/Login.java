package umn.ac.id.uas_suits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class Login extends AppCompatActivity {

    TextView reg;
    private EditText Lemail, Lpassword;
    private FirebaseAuth profileAuth;
    private static final String TAG="Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = (TextView) findViewById(R.id.register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        getSupportActionBar().setTitle("Login");

        Lemail = findViewById(R.id.email_login_input);
        Lpassword = findViewById(R.id.password_login_input);

        profileAuth = FirebaseAuth.getInstance();

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = Lemail.getText().toString();
                String textPassword = Lpassword.getText().toString();

                if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(Login.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                    Lemail.setError("Email is Required");
                    Lemail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(Login.this, "Please re-enter Your Email", Toast.LENGTH_SHORT).show();
                    Lemail.setError("Valid Email is Required");
                    Lemail.requestFocus();
                } else if (TextUtils.isEmpty(textPassword)) {
                    Toast.makeText(Login.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    Lpassword.setError("Password is Required");
                    Lpassword.requestFocus();
                } else {
                    loginUser(textEmail,textPassword);
                }
            }
        });

    }

    private void loginUser(String email, String password) {
        profileAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "You are Logged In Now", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        Lemail.setError("User does not exist");
                        Lemail.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        Lemail.setError("Invalid Credentials");
                        Lemail.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Login.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}