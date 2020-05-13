package com.example.lesaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "hmm";
    private String message;
    private EditText editText ;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Pindah(View view) {
        message="+62";
        editText = (EditText)findViewById(R.id.notelp);
        String tambah = editText.getText().toString();
        message=message.concat(tambah);
        database = FirebaseDatabase.getInstance().getReference();
//        Query query=database.child("users").orderByChild("notelp").equalTo(message);

        if (message.length() == 0){
            editText.setError("Harap isi Nomor Telepon Anda");
        }
        else if (message.length() < 11 || message.length() > 15){
            editText.setError("Nomor Telepon Anda tidak valid");
        }
        else{
            database.child("Users")
                    .orderByChild("notelp")
                    .equalTo(message)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String clubkey="";

                            for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                clubkey = childSnapshot.getKey();
                            }
                            if (clubkey.equals("")){
                                editText.setError("Nomor Telepon Tidak terdaftar");
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this, VerifActivity.class);
                                intent.putExtra(EXTRA_MESSAGE,message);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            editText.setError("Nomor Telepon Tidak terdaftar");
                        }
                    });
        }

    }
    public void PindahRegis(View view) {
            Intent intentRegis = new Intent(MainActivity.this, RegisActivity.class);
            startActivity(intentRegis);
    }
}
