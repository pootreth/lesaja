package com.example.lesaja;

import androidx.annotation.NonNull;
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

import static com.example.lesaja.RegisActivity.isEmailValid;

public class ProfileEdit extends AppCompatActivity {
    private String uid;
    private EditText nama;
    private EditText notelp;
    private EditText email;
    private String indo;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        nama=findViewById(R.id.Id);
        notelp=findViewById(R.id.idd);
        email=findViewById(R.id.id2);
        indo="+62";
        ref= FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
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
                keys=keys.substring(3);
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
    public void saves(View view){
        if(nama.getText().toString().length()==0 || notelp.getText().toString().length()==0 || email.getText().toString().length()==1)
        {
            if(notelp.getText().toString().length()==1)
            {
                notelp.setError("Harap isi Nomor Telepon Anda");
            }
            if(nama.getText().toString().length()==0)
            {
                nama.setError("Harap isi Nama Anda");
            }
            if(email.getText().toString().length()==0)
            {
                email.setError("Harap isi Email Anda");
            }
        }
        else if (notelp.getText().toString().length() < 9 || notelp.getText().toString().length() > 15 || !isEmailValid(email.getText().toString())){
            if(!isEmailValid(email.getText().toString())){
                email.setError("Email Anda tidak valid");
            }
            if(notelp.getText().toString().length() < 9 || notelp.getText().toString().length() > 15) {
                notelp.setError("Nomor Telepon Anda tidak valid");
            }
        }
        else{
            String namaa= nama.getText().toString();
            String emaill= email.getText().toString();
            String notelpp = indo.concat(notelp.getText().toString());

            ref.child("nama").setValue(namaa);
            ref.child("notelp").setValue(notelpp);
            ref.child("email").setValue(emaill);

            Intent save=  new Intent(ProfileEdit.this, ProfileActivity.class);
            startActivity(save);
            finish();
        }
    }
    public void Balikk(View arg0){
        finish();
    }
    }
