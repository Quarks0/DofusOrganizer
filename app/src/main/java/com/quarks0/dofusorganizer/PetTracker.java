package com.quarks0.dofusorganizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.view.View;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PetTracker extends AppCompatActivity{
    List<String> petList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState != null){
            //listChild = (HashMap<String, List<ListItem>>) savedInstanceState.getSerializable("EHmap");
            //listHeader = savedInstanceState.getStringArrayList("EHheader");
        }
        else{
            petList = pullFromCSV();
        }

        String petinput; //keep track!
        AutoCompleteTextView petselect = (AutoCompleteTextView) findViewById(R.id.petAutoCompleteTextView);
        petselect.setAdapter(my_adapter);
        petselect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                petinput = my_adapter.getItem(position).toString();
            }
        });
/**
 * Unset the var whenever the user types. Validation will
 * then fail. This is how we enforce selecting from the list.
 */
        petselect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                petinput = null;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void addPet(){
        Pet pet = new Pet(getPetInfo(0));
    }

    public void delPet(){

    }



    private List<String> pullFromCSV(){
        InputStream inputStream = getResources().openRawResource(R.raw.DofusPet);
        CSVFile csvFile = new CSVFile(inputStream);

        return csvFile.read();
    }

    private List pullFromPetList(){
        List<String> petnames = new ArrayList<>();
        for(int i = 0;i<petList.size();i++){
            petnames.add(petList.get(i).split(",")[0]);
        }
        return petnames;
    }

    private String[] getPetInfo(int lineNum){
        return petList.get(lineNum).split(",");
    }
}

class Pet{
    final private String petName;
    final private long[] feedTimer;
    private long lastFed;
    private int healthPoints;
    final private String feedMap;

    public Pet(String[] petInfo){
        long hours_to_millis = 3600000;
        petName = petInfo[0];
        feedTimer = new long[]{Long.valueOf(petInfo[1])*hours_to_millis,Long.valueOf(petInfo[2])*hours_to_millis};
        feedMap = petInfo[3];
        lastFed = getCurrentTime();
        healthPoints = 10;
    }

    private long getCurrentTime(){
        return Calendar.getInstance().getTimeInMillis();
    }

    public String getPetName(){
        return petName;
    }

    public int getHP(){
        return healthPoints;
    }

    public String getFeedMap(){
        return feedMap;
    }

    public long[] getFeedWindow(){
        return new long[] {lastFed+feedTimer[0], lastFed+feedTimer[1]};
    }

    public void incHP(){
        if (healthPoints < 10) {
            healthPoints++;
        }
    }

    public void decHP() {
        if (healthPoints > 0){
            healthPoints--;
        }
    }

    public void feed(){
        lastFed = getCurrentTime();
        if(!inFeedWindow()){
            decHP();
        }
    }

    public boolean inFeedWindow(){
        return lastFed+feedTimer[0] < getCurrentTime() && lastFed+feedTimer[1] > getCurrentTime();
    }
}