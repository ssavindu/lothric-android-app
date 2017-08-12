package com.example.savindusanjana.lothric;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class JournalFrag extends Fragment implements View.OnClickListener{

    FloatingActionButton fab;

    View view;


    public JournalFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_journal, container, false);
        fab =(FloatingActionButton) view.findViewById(R.id.addJournal);
        fab.setOnClickListener(this);
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


    

}
