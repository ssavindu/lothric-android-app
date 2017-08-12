package com.example.savindusanjana.lothric;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFrag extends Fragment implements View.OnClickListener{
    ArrayList <String> arrayList;
    ArrayAdapter <String> arrayAdapter;

    ListView lv;

    FloatingActionButton upButton;
    View view;

    String activity_set_id;
    public ToDoFrag() {
        // Required empty public constructor
    }

    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_to_do, container, false);
        upButton = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        upButton.setOnClickListener(this);

        lv = (ListView) view.findViewById(R.id.list);
        tv = (TextView) view.findViewById(R.id.sampleText);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_multiple_choice, arrayList);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv.setAdapter(arrayAdapter);

        return view;
    }

    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    DatabaseReference activities = root.child("activities");
    DatabaseReference userID = activities.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    DatabaseReference currentValue = userID.child("latest_activity_set");




    @Override
    public void onStart() {
        super.onStart();

        currentValue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                activity_set_id = dataSnapshot.getValue(String.class);

                DatabaseReference root2 = FirebaseDatabase.getInstance().getReference();
                DatabaseReference activity_sets = root2.child("activity_sets");
                DatabaseReference activity_set = activity_sets.child("activity_set");
                DatabaseReference set = activity_set.child(activity_set_id);

                set.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String,Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();

                        Set keys = map.keySet();
                        arrayList.clear();
                        arrayAdapter.notifyDataSetChanged();

                        for (Iterator i = keys.iterator(); i.hasNext(); ) {
                            String key = (String) i.next();
                          //  String state = map.get(key)+"";



                            if(!(key.equals("end_day")|key.equals("start_day"))){

                                arrayList.add(key);

//                                if(state.contains("true")){
//                                    lv.setItemChecked(0,true);
//                                }
                            }


                        }
                        arrayAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                //Button code here
                DatabaseReference root2 = FirebaseDatabase.getInstance().getReference();
                DatabaseReference activity_sets = root2.child("activity_sets");
                DatabaseReference activity_set = activity_sets.child("activity_set");
                DatabaseReference set = activity_set.child(activity_set_id);


                int len = lv.getCount();
                SparseBooleanArray checked = lv.getCheckedItemPositions();
                for (int i = 0; i < len; i++)
                    if (checked.get(i)) {
                        String item = arrayList.get(i);

                        DatabaseReference task = set.child(item);
                        task.setValue("true");

                    }else{
                        String item = arrayList.get(i);
                        DatabaseReference task = set.child(item);
                        task.setValue("false");

                    }

                Toast.makeText(view.getContext(),"Updated",Toast.LENGTH_SHORT).show();

                break;

        }
    }

}
