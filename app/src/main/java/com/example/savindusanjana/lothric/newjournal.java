package com.example.savindusanjana.lothric;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by savindusanjana on 8/12/17.
 */

public class newjournal extends AppCompatActivity {
    EditText txtSubject;
    EditText txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newjournal);

        txtSubject = (EditText) findViewById(R.id.txtTitle);
        txtContent = (EditText) findViewById(R.id.txtContent);

    }

    public void saveJournal(View view){
        String subject = txtSubject.getText().toString();
        String content = txtContent.getText().toString();
        String journalId = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+subject;

        String dateLocal = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String timeLocal = new SimpleDateFormat("HH:mm:ss").format(new Date());

        String timestamp = (System.currentTimeMillis() / 1000)+"";


        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference journals = root.child("Journals");
        DatabaseReference userID = journals.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        DatabaseReference id = userID.child(journalId);
        id.setValue(true);



        DatabaseReference journal_entity = root.child("journal-entity");
        DatabaseReference jID = journal_entity.child(journalId);
        DatabaseReference title = jID.child("title");
        title.setValue(subject);
        DatabaseReference body = jID.child("body");
        body.setValue(content);
        DatabaseReference timeStamp = jID.child("timestamp");
        timeStamp.setValue(timestamp);
        DatabaseReference time = jID.child("time");
        time.setValue(timeLocal);
        DatabaseReference date = jID.child("date");
        date.setValue(dateLocal);

        finish();


    }
}
