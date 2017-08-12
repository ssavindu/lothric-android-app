package com.example.savindusanjana.lothric;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by savindusanjana on 8/12/17.
 */

public class tabs extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    viewPagerAdapter vpa;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        vpa = new viewPagerAdapter(getSupportFragmentManager()) ;

        vpa.addFragments(new homeFrag(),"Home");
        vpa.addFragments(new JournalFrag(),"Journal");
        vpa.addFragments(new ToDoFrag(),"ToDo");

        viewPager.setAdapter(vpa);
        tabLayout.setupWithViewPager(viewPager);

        TextView welcm_txt = (TextView) findViewById(R.id.user_welcome);
        welcm_txt.setText("Welcome"+user.getDisplayName());
    }

    @Override
    public void onStart(){
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }


}
