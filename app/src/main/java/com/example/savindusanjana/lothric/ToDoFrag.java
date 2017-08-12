package com.example.savindusanjana.lothric;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFrag extends Fragment implements View.OnClickListener{
    ArrayList <String> arrayList;
    ArrayAdapter <String> arrayAdapter;

    ListView lv;

    FloatingActionButton upButton;
    View view;
    public ToDoFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_to_do, container, false);
        upButton = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        upButton.setOnClickListener(this);
        lv = (ListView) view.findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_list_item_1, arrayList);

        lv.setAdapter(arrayAdapter);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                Intent intent = new Intent(getActivity(),newItem.class);
                startActivityForResult(intent,0);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            arrayList.add(data.getExtras().getString("ITEM"));
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
