package com.example.lesaja;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.graphics.Color;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    private String uid;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth= FirebaseAuth.getInstance();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_saldotrx, R.id.nav_setting,
                R.id.nav_help, R.id.nav_jadwalles)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        super.onStart();
        FirebaseUser currentUser= mAuth.getCurrentUser();

        if(currentUser==null){
            Intent loginIntent= new Intent(HomeActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("nama");
        ref.addValueEventListener(new ValueEventListener(){
            String keys;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                keys=dataSnapshot.getValue(String.class);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.namee);
                navUsername.setText(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        DatabaseReference ref2= FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("email");
        ref2.addValueEventListener(new ValueEventListener(){
            String keys;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                keys=dataSnapshot.getValue(String.class);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.textView);
                navUsername.setText(keys);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }



    public void pindahh(View v){
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.action_settings)
        {
            mAuth.signOut();
            Intent loginIntent= new Intent(HomeActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
