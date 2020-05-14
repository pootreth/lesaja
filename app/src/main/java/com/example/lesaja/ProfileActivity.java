package com.example.lesaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private String uid;
    private TextView nama;
    private TextView notelp;
    private TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        nama=findViewById(R.id.Id11);
        notelp=findViewById(R.id.id12);
        email=findViewById(R.id.id13);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        ref.child("nama").addValueEventListener(new ValueEventListener(){
            String keys;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                keys=dataSnapshot.getValue(String.class);
                nama.setText(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("notelp").addValueEventListener(new ValueEventListener(){
            String keys;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                keys=dataSnapshot.getValue(String.class);
                notelp.setText(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("email").addValueEventListener(new ValueEventListener(){
            String keys;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                keys=dataSnapshot.getValue(String.class);
                email.setText(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void editt(View view){
        Intent edit=  new Intent(ProfileActivity.this, ProfileEdit.class);
        startActivity(edit);
        finish();
    }
    public void Balikkk(View arg0){
        finish();
    }
}
