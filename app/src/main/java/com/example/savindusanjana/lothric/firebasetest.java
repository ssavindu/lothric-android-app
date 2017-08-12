package com.example.savindusanjana.lothric;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by savindusanjana on 8/12/17.
 */

public class firebasetest extends Activity{
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebasetest);
        textView = (TextView) findViewById(R.id.txtState);
    }

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
 //   DatabaseReference key = root.child("key");


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        key.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                textView.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    public void btn1(View v){
        DatabaseReference child = root.child("Name");

        DatabaseReference sec_lev = child.child("sec_lev");
        sec_lev.setValue("Americnao");
    }

    public void btn2(View view){
        //key.setValue("second");
    }
}
