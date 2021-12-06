package umn.ac.id.uas_suits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    private EditText RFullName, REmail, RDoB, RPhone, RPassword1, RPassword2;
    private RadioGroup RradioGroup;
    private RadioButton RGender;
    private DatePickerDialog picker;
    private static final String TAG="Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");
        Toast.makeText(Register.this, "you can register now", Toast.LENGTH_LONG).show();

        RFullName = findViewById(R.id.FullName);
        REmail = findViewById(R.id.Email_input);
        RDoB = findViewById(R.id.bDate);
        RPhone = findViewById(R.id.Phone);
        RPassword1 = findViewById(R.id.Password1);
        RPassword2 = findViewById(R.id.Password2);

        RradioGroup = findViewById(R.id.radio_gender);
        RradioGroup.clearCheck();

        RDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        RDoB.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year,month,day);
                picker.show();
            }
        });


        Button registerButton = findViewById(R.id.Register_Button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedGenderId = RradioGroup.getCheckedRadioButtonId();
                RGender = findViewById(selectedGenderId);

                String textFullName = RFullName.getText().toString();
                String textEmail = REmail.getText().toString();
                String textDoB = RDoB.getText().toString();
                String textPhone = RPhone.getText().toString();
                String textPassword1 = RPassword1.getText().toString();
                String textPassword2 = RPassword2.getText().toString();
                String textGender;

                if (TextUtils.isEmpty(textFullName)) {
                    Toast.makeText(Register.this, "Please Enter Your Full Name", Toast.LENGTH_SHORT).show();
                    RFullName.setError("Full Name is Required");
                    RFullName.requestFocus();
                } else if (TextUtils.isEmpty(textEmail)) {
                    Toast.makeText(Register.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                    REmail.setError("Email is Required");
                    REmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textEmail).matches()) {
                    Toast.makeText(Register.this, "Please Re-enter Your Email", Toast.LENGTH_SHORT).show();
                    REmail.setError("Valid Email is Required");
                    REmail.requestFocus();
                } else if (TextUtils.isEmpty(textDoB)) {
                    Toast.makeText(Register.this, "Please Enter Your Date of Birth", Toast.LENGTH_SHORT).show();
                    RDoB.setError("Date of Birth is Required");
                    RDoB.requestFocus();
                } else if (RradioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(Register.this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
                    RGender.setError("Gender is Required");
                    RGender.requestFocus();
                } else if (TextUtils.isEmpty(textPhone)) {
                    Toast.makeText(Register.this, "Please Enter Your Date of Birth", Toast.LENGTH_SHORT).show();
                    RPhone.setError("Date of Birth is Required");
                    RPhone.requestFocus();
                } else if (textPhone.length() > 12) {
                    Toast.makeText(Register.this, "Please re-enter your mobile no.", Toast.LENGTH_SHORT).show();
                    RPhone.setError("Mobile Number should not be Over 12 Digit");
                    RPhone.requestFocus();
                } else if (TextUtils.isEmpty(textPassword1)) {
                    Toast.makeText(Register.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    RPassword1.setError("Password is Required");
                    RPassword1.requestFocus();
                } else if (textPassword1.length() < 6) {
                    Toast.makeText(Register.this, "Password should be at least 6 letter or digit", Toast.LENGTH_SHORT).show();
                    RPassword1.setError("Password to Weak");
                    RPassword1.requestFocus();
                } else if (TextUtils.isEmpty(textPassword2)){
                    Toast.makeText(Register.this, "Please Confirm Your Password", Toast.LENGTH_SHORT).show();
                    RPassword2.setError("Password Confirmation is Required");
                    RPassword2.requestFocus();
                } else if (!textPassword1.equals(textPassword2)) {
                    Toast.makeText(Register.this, "Your Confirmation Password Different with your Password", Toast.LENGTH_SHORT).show();
                    RPassword2.setError("Confirmation Password must be Same as Your Password");
                    RPassword2.requestFocus();

                    RPassword1.clearComposingText();
                    RPassword2.clearComposingText();
                } else {
                    textGender = RGender.getText().toString();
                    registerUser(textFullName, textEmail, textDoB, textGender, textPhone, textPassword1);
                }

            }
        });
    }

    private void registerUser(String textFullName, String textEmail, String textDoB, String textGender, String textPhone, String textPassword1) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.createUserWithEmailAndPassword(textEmail, textPassword1).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = fAuth.getCurrentUser();

                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(textFullName).build();
                    firebaseUser.updateProfile(profileChangeRequest);

                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(textDoB, textPhone, textGender);
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance("https://uas-suit-s-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");

                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                //firebaseUser.sendEmailVerification();

                                Toast.makeText(Register.this, "User Registered Successfully. Please Verify Your Email", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Register.this, Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(Register.this, "User Registered Failed. Please Try Again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        RPassword1.setError("Your Password is too Weak. Kindly use a mix of alphabets, number and special characters");
                        RPassword1.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        RPassword1.setError("Your Email is Invalid or Already Used. Kindly re-enter Your Email");
                        RPassword1.requestFocus();
                    } catch (FirebaseAuthUserCollisionException e) {
                        RPassword1.setError("User Already Registered with this email. User Another Email");
                        RPassword1.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}