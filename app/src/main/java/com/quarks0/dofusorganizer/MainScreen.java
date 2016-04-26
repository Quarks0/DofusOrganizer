package com.quarks0.dofusorganizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Brings you to Character View
    public void viewCharacters(View view) {
        Intent intent = new Intent(this,DisplayCharacters.class);
        startActivity(intent);

        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
    }

    //Brings you to Eternal Harvest tracking
    public void harvestTracker(View view) {
        Intent intent = new Intent(this,HarvestTracker.class);
        startActivity(intent);
    }

    //Debatable marketplace tracking
    public void marketTracker(View view) {
        //Intent intent = new Intent(this,MarketTracker.class);
        //startActivity(intent);
        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
    }

    //Brings you to pet feeding timers
    public void petTracker(View view) {
        //Intent intent = new Intent(this, PetTracker.class);
        //startActivity(intent);
        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
    }

    //Brings you to Almanax Calendar
    public void almanaxCalendar(View view){
        //Intent intent = new Intent(this, almanaxCalendar.class);
        //startActivity(intent);
        Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
    }
}
