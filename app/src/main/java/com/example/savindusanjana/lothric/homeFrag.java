package com.example.savindusanjana.lothric;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class homeFrag extends Fragment {

    public TextView txtQuote;
    public static String quote;



    public homeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcome_text = (TextView) view.findViewById(R.id.user_welcome);
        txtQuote = (TextView) view.findViewById(R.id.quoteText);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        welcome_text.setText("Welcome "+user.getDisplayName());

        new Thread(){
            @Override
            public void run(){
                HTTPHANDLER t = new HTTPHANDLER();
                t.execute();
            }
        }.start();


        return view;
    }


    public void updateUI(){
        txtQuote.setText("'"+quote+"'");
    }
    private class HTTPHANDLER extends AsyncTask<Void,Void,Void> {

        public final String TAG = HTTPHANDLER.class.getCanonicalName();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url  = new URL("http://quotes.rest/qod.json?category=inspire");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                Log.i(TAG,"connection opened");
                connection.setRequestMethod("GET");
                InputStream in = new BufferedInputStream((connection.getInputStream()));
                Log.i(TAG,"input stream connected");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder builder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line).append("\n");
                }
                in.close();
                String response = builder.toString();
                Log.i(TAG,"recieved response");
                //Log.i(TAG,response);
                JSONObject root = new JSONObject(response);
                JSONObject contents = root.getJSONObject("contents");
                JSONArray quotes = contents.getJSONArray("quotes");
                for(int i =0;i<quotes.length();i++){
                    JSONObject quote = quotes.getJSONObject(i);
                    String quote_text = quote.getString("quote");
                    Log.i(TAG,"recieved quote");
                    Log.i(TAG,quote_text);
                    String s = quote_text;
                    setQuote(s);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            updateUI();
        }
    }


    public static void setQuote(String q){
        quote = q;

    }

}
