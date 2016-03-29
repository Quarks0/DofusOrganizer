package com.quarks0.dofusorganizer;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HarvestTracker extends AppCompatActivity {

    ExpandableListAdapter eHList;
    ExpandableListView eHListView;
    List<String> listHeader;
    HashMap<String,List<String>> listChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_tracker);
        eHListView = (ExpandableListView) findViewById(R.id.eternalHarvestList);
        initiateList();
        eHList = new ExpandableListAdapter();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initiateList() { //Populates the EH list
        listHeader = new ArrayList<String>();
        listChild = new HashMap<String,List<String>>();

        //Parent menu
        for(int i=0; i<35;i++) {
            listHeader.add("Step "+i);
        }

        //Child menus
        List<String> step1 = new ArrayList<String>();
        step1.add("Grey Mouse");
        step1.add("Moskito");
        step1.add("Arachnee");
        step1.add("White Gobbly");
        step1.add("Black Gobbly");
        step1.add("Evil Tofu");
        step1.add("Sick Arachnee");
        step1.add("Blue Piwi");
        step1.add("Red Piwi");
        step1.add("Yellow Piwi");
        step1.add("Green Piwi");
        step1.add("Purple Piwi");
        step1.add("Air Spark");
        step1.add("Water Spark");
        step1.add("Fire Spark");
        step1.add("Earth Spark");
        step1.add("Blue Larva");
        step1.add("Crab");
        step1.add("Pink Piwi");
        step1.add("Tofu");

        List<String> step2 = new ArrayList<String>();
        step1.add("Grey Mouse");
        step1.add("Moskito");
        step1.add("Arachnee");
        step1.add("White Gobbly");
        step1.add("Black Gobbly");
        step1.add("Evil Tofu");
        step1.add("Sick Arachnee");
        step1.add("Blue Piwi");
        step1.add("Red Piwi");
        step1.add("Yellow Piwi");
        step1.add("Green Piwi");
        step1.add("Purple Piwi");
        step1.add("Air Spark");
        step1.add("Water Spark");
        step1.add("Fire Spark");
        step1.add("Earth Spark");
        step1.add("Blue Larva");
        step1.add("Crab");
        step1.add("Pink Piwi");
        step1.add("Tofu");

        List<String> step3 = new ArrayList<String>();
        step1.add("Kokonut");
        step1.add("Tikoko");
        step1.add("Glukoko");
        step1.add("Boar");
        step1.add("Green Spimush");
        step1.add("Pumpkwin");
        step1.add("Vampyre");
        step1.add("Coco Biblop");
        step1.add("Morello Cherry Biblop");
        step1.add("Indigo Biblop");
        step1.add("Pippin Biblop");
        step1.add("Karne Rider");
        step1.add("Sick Grossewer Milirat");
        step1.add("Mushd");
        step1.add("Prespic");
        step1.add("Piglet");
        step1.add("Yellow Turtle");
        step1.add("Wabbit");
        step1.add("Gobbal War Chief");
        step1.add("Lousy Pig Shepherd");

        List<String> step4 = new ArrayList<String>();
        step1.add("Whitish Fang");
        step1.add("Bulbamboo");
        step1.add("Blue Turtle");
        step1.add("Crow");
        step1.add("Starfish Trooper");
        step1.add("White Snapper");
        step1.add("Blue Snapper");
        step1.add("Orange Snapper");
        step1.add("Green Snapper");
        step1.add("Bambooto");
        step1.add("Red Turtle");
        step1.add("Vampyre Master");
        step1.add("Minoskito");
        step1.add("Neye");
        step1.add("Miliboowolf");
        step1.add("Ambusher");
        step1.add("Bulbiflor");
        step1.add("Green Turtle");
        step1.add("Bwork Magus");
        step1.add("Furious Whitish Fang");

        List<String> step5 = new ArrayList<String>();
        step1.add("Dark Rose");
        step1.add("Black Wabbit");
        step1.add("Dark Miner");
        step1.add("Bwork Archer");
        step1.add("Blue Spimush");
        step1.add("Brown Spimush");
        step1.add("Red Spimush");
        step1.add("Crackrock");
        step1.add("Manderisha");
        step1.add("Kitsou Nakwa");
        step1.add("Gargoyl");
        step1.add("Wild Almond Dragoturkey");
        step1.add("Strawberry Jelly");
        step1.add("Skeleton Wabbit");
        step1.add("Invisible Chafer");
        step1.add("Crackler");
        step1.add("Bwork");
        step1.add("Treechnid");
        step1.add("Khamelrost");

        List<String> step6 = new ArrayList<String>();
        step1.add("White Scaraleaf");
        step1.add("Blue Scaraleaf");
        step1.add("Red Scaraleaf");
        step1.add("Green Scaraleaf");
        step1.add("Rib");
        step1.add("Coco Blop");
        step1.add("Morello Cherry Blop");
        step1.add("Indigo Blop");
        step1.add("Pippin Blop");
        step1.add("Farle's Pig");
        step1.add("Scaratos");
        step1.add("Boomba");
        step1.add("Trunknid");
        step1.add("Chafter Foot Soldier");
        step1.add("Kloon Snapper");
        step1.add("Treechnee");
        step1.add("Chafer");
        step1.add("Crocodyl");
        step1.add("Sick Grossewer Rat");
        step1.add("Scurvion");

        List<String> step7 = new ArrayList<String>();
        step1.add("Wild Ginger Dragoturkey");
        step1.add("Dark Smith");
        step1.add("Kurasso Craboral");
        step1.add("Mahlibuh Craboral");
        step1.add("Mojeeto Craboral");
        step1.add("Passaoh Craboral");
        step1.add("Bulbush");
        step1.add("Major Arachnee");
        step1.add("Araknawa");
        step1.add("Gwandpa Wabbit");
        step1.add("Wo Wabbit");
        step1.add("Lousy Pig Knight");
        step1.add("Aboub");
        step1.add("Amlub");
        step1.add("Codem");
        step1.add("Gink");
        step1.add("Kirevam");
        step1.add("Let Emoliug");
        step1.add("Nebgib");
        step1.add("Nipul");

        List<String> step8 = new ArrayList<String>();
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");

        List<String> step9 = new ArrayList<String>();
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");

        List<String> step10 = new ArrayList<String>();
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");

        List<String> step11 = new ArrayList<String>();
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");

        List<String> step12 = new ArrayList<String>();
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");

        List<String> step13 = new ArrayList<String>();
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");
        step1.add("");

        //Hooboy, now put them together
        listChild.put(listHeader.get(0),step1);
        listChild.put(listHeader.get(1),step2);
        listChild.put(listHeader.get(2),step3);
        listChild.put(listHeader.get(3),step4);
        listChild.put(listHeader.get(4),step5);
        listChild.put(listHeader.get(5),step6);
        listChild.put(listHeader.get(6),step7);
        listChild.put(listHeader.get(7),step8);
        listChild.put(listHeader.get(8),step9);

    }

}
