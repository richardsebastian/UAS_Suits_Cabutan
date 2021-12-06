package umn.ac.id.uas_suits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WellcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome_page);

        getSupportActionBar().setTitle("Suit's App");

        Button buttonGetStarted = findViewById(R.id.to_login_page);
        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WellcomePage.this, Login.class);
                startActivity(intent);
            }
        });
    }
}