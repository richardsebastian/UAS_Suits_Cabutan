package umn.ac.id.uas_suits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Order extends AppCompatActivity {

    EditText fName,jName,address,phoneN,size,dateO,dateR;
    Button orderBtn;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        fStore = FirebaseFirestore.getInstance();
        orderBtn = findViewById(R.id.btnOrder);
        fName = findViewById(R.id.inputNama);
        jName = findViewById(R.id.InputJas);
        address = findViewById(R.id.InputAlamat);
        phoneN = findViewById(R.id.inputHP);
        size = findViewById(R.id.InputSize);
        dateO = findViewById(R.id.InputMeminjam);
        dateR = findViewById(R.id.InputPengembalian);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FullName = fName.getText().toString();
                String SuitName = jName.getText().toString();
                String Address = address.getText().toString();
                String Phone = phoneN.getText().toString();
                String Size = size.getText().toString();
                String OrderDate = dateO.getText().toString();
                String ReturnDate = dateR.getText().toString();
                Map<String,Object> order = new HashMap<>();
                order.put("Full Name",FullName);
                order.put("Suit Name",SuitName);
                order.put("Address",Address);
                order.put("Phone",Phone);
                order.put("Size",Size);
                order.put("Order Date",OrderDate);
                order.put("Return Date",ReturnDate);

                fStore.collection("order").add(order).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference) {
                        Toast.makeText(Order.this, "Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Order.this, "Failed",Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(Order.this, OrderSuccess.class));
            }
        });
    }

}