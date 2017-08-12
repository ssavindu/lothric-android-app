package com.example.savindusanjana.lothric;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeFrag extends Fragment {


    public homeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcome_text = (TextView) view.findViewById(R.id.user_welcome);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        welcome_text.setText("Welcome oi "+user.getDisplayName());
        return view;
    }

}
