package com.example.savindusanjana.lothric;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class JournalFrag extends Fragment implements View.OnClickListener{

    FloatingActionButton fab;

    View view;


    public JournalFrag() {
        // Required empty public constructor
    }



    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference journals = root.child("Journals");

    DatabaseReference usr = journals.child(userID);
    //TextView journalText;

    ArrayList<String> arrayList;
    ArrayAdapter <String> arrayAdapter;
    ListView listView;

    @Override
    public void onStart() {
        super.onStart();
        usr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                arrayAdapter.notifyDataSetChanged();
                Map <String,Boolean> map = (Map<String,Boolean>) dataSnapshot.getValue();

                Set keys = map.keySet();
                for (Iterator i = keys.iterator(); i.hasNext(); ) {
                    String key = (String) i.next();

                    DatabaseReference root2 = FirebaseDatabase.getInstance().getReference();
                    final DatabaseReference journal_entity = root2.child("journal-entity");
                    DatabaseReference row = journal_entity.child(key);

                    row.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Map<String,String> map = (Map<String, String>) dataSnapshot.getValue();

                            String title = map.get("title");
                            String body = map.get("body");

                            arrayList.add(title+"\n"+body);
                            arrayAdapter.notifyDataSetChanged();

                            //journalText.setText(journalText.getText()+title+"-"+body+", ");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_journal, container, false);
        fab =(FloatingActionButton) view.findViewById(R.id.addJournal);
      //  journalText = (TextView) view.findViewById(R.id.journalText);
        fab.setOnClickListener(this);


        listView = (ListView) view.findViewById(R.id.journalList);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);


        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addJournal:
                Intent intent = new Intent(getActivity(),newjournal.class);
                startActivity(intent);
                break;

        }
    }

    private void loadJournals(){


    }
}
