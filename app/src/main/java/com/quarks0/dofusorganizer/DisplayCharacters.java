package com.quarks0.dofusorganizer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayCharacters extends AppCompatActivity {

    public final static String GUILD_NAME = "com.quarks0.dofusorganizer.guildName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_characters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


//    public void addItems(View view){
//        listItems.add()
//    }

    public void charSearch(View view){
        //Looks up guild
        EditText guildName = (EditText)findViewById(R.id.guildName);
        String guildQuery = "http://www.dofus.com/en/mmorpg/community/directories/guild-pages?TEXT="+guildName.getText().toString().replaceAll(" ","+")+"&guild_level_min=1&guild_level_max=200&=#jt_list";

//        URL guildSearch = null;
//        HttpURLConnection urlConnection = null;
//        String result = null;
//
//        //Attempt to search guild name
//        try {
//            guildSearch = new URL("http://www.dofus.com/en/mmorpg/community/directories/guild-pages?TEXT="+guildQuery+"&guild_level_min=1&guild_level_max=200&=#jt_list");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            urlConnection = (HttpURLConnection) guildSearch.openConnection();
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            result = readStream(in);
//            System.out.print(result);
//        }   catch (IOException e){
//            e.printStackTrace();
//        }
//        finally{
//            urlConnection.disconnect();
//        }

        //Pass results to CharacterSearch
        Intent CharSearch = new Intent(this,CharacterSearch.class);
        CharSearch.putExtra(GUILD_NAME,guildQuery);
        startActivity(CharSearch);
    }

//    private String readStream(InputStream is) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
//        for (String line = r.readLine(); line != null; line =r.readLine()){
//            sb.append(line);
//        }
//        is.close();
//        return sb.toString();
//    }
}
