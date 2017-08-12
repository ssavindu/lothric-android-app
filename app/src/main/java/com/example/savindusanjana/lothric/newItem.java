package com.example.savindusanjana.lothric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by savindusanjana on 8/12/17.
 */

public class newItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newitem);
    }

    public void save(View v){
        EditText txt = (EditText) findViewById(R.id.txtItem);
        String item = txt.getText().toString();
        Intent intent = new Intent(newItem.this,ToDoFrag.class);
        intent.putExtra("ITEM",item);
        setResult(0,intent);
        finish();

    }
}
