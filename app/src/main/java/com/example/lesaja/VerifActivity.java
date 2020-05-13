package com.example.lesaja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifActivity extends AppCompatActivity {
    private String verificationid;
    private PhoneAuthProvider.ForceResendingToken resend;
    private FirebaseAuth Mauth;
    private EditText editext;
    private DatabaseReference database;
    private String message ;
    private String message2;
    private String message2Nomor;
    private String message2Nama;
    private String message2Email;
    private String[] parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verif);

        Mauth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("Users");
        editext=findViewById(R.id.editText);

        Intent intent = getIntent();
        //mengambil nilai dari intent

        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        message2 = intent.getStringExtra(RegisActivity.EXTRA_MESSAGEEE);
        TextView textView = (TextView)findViewById(R.id.textView);
            parts = message2.split("-");
            if(message2.length()>15)
            {
                message2Nomor=parts[0];
                message2Nama=parts[1];
                message2Email=parts[2];
            }
            else
            {
                message2Nomor=parts[0];
            }
            textView.setText(message2Nomor);
            sendVerifCode(message2Nomor);

    }

    public void Pindah(View view) {
        String code=editext.getText().toString();
        if(code.isEmpty() || code.length() < 6) {
            editext.setError("Kode Anda tidak valid");
            editext.requestFocus();
            return;
        }
        verifCode(code);

    }
    public void verifCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signInWithCredential(credential);
    }
    public void signInWithCredential(PhoneAuthCredential credential)
    {
        Mauth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user=task.getResult().getUser();
                            if(message2Nama!= null){
                                String user_id=Mauth.getCurrentUser().getUid();
                                DatabaseReference id_db = database.child(user_id);
                                id_db.child("nama").setValue(message2Nama);
                                id_db.child("notelp").setValue(message2Nomor);
                                id_db.child("email").setValue(message2Email);
                                id_db.child("saldo").setValue(0);
                            }

                            Intent intent2 = new Intent(VerifActivity.this, HomeActivity.class);
                            startActivity(intent2);
                            finish();
                        }
                        else{
                            editext.setError("Kode Anda salah");
                            editext.requestFocus();
                        }
                    }
                });
    }

    public void sendVerifCode(String number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            verificationid=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            String code= credential.getSmsCode();
            if(code!=null)
            {
                verifCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    };
    public void Balik(View arg0) {
        finish();
    }
}
