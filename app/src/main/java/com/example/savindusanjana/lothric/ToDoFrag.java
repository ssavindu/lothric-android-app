package com.example.savindusanjana.lothric;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFrag extends Fragment implements View.OnClickListener{
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

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
        return view;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                Intent intent = new Intent(getActivity(),newItem.class);
                startActivity(intent);
                break;


        }
    }
}
