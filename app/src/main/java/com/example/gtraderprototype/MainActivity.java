package com.example.gtraderprototype;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectDB();
    }

    static void connectDB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("message");


        /* -----Sample Player Class creation and database push
        String name = "Tyler";
        int difficulty = 2;
        double skill1 = 0.5;
        double skill2 = 0.3;

        DatabaseReference users = database.getReference("users");
        DatabaseReference newuser = users.child("newuserid2");

        Player myplayer = new Player(name, difficulty);
        myplayer.addSkill("Engineer", 0.5);
        myplayer.addSkill("Fighter", 0.3);
        myplayer.addSkill("Trader", 0.2);

        newuser.setValue(myplayer);

        newuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();

                Log.d("Hi", "Value is: " + value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error: "+ databaseError);
            }
        });

*/
        //Sets var message to "Hello, World!"
        myRef.setValue("Hello, World!");

    }
}
