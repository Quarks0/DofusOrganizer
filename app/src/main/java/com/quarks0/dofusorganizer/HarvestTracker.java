package com.quarks0.dofusorganizer;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HarvestTracker extends AppCompatActivity {

    ExpandableListAdapter eHListAdapter;
    ExpandableListView eHListView;
    List<String> listHeader;
    HashMap<String,List<ListItem>> listChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_tracker);
        eHListView = (ExpandableListView) findViewById(R.id.eternalHarvestList);
        initiateList();
        eHListAdapter = new ExpandableListAdapter(this, listHeader,listChild);
        eHListView.setAdapter(eHListAdapter);

        eHListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        eHListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(), listHeader.get(groupPosition) + " collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        eHListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ColorDrawable bgColor = (ColorDrawable) v.getBackground();
                int bgColorint = bgColor.getColor();
                if (bgColorint == Color.parseColor("#FFFFFF")) {
                    v.setBackgroundColor(Color.parseColor("#41A317"));
                    Toast.makeText(getApplicationContext(), "Get!", Toast.LENGTH_SHORT).show();
                } else {
                    v.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }

                return true;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initiateList() { //Populates the EH list
        listHeader = new ArrayList<>();
        listChild = new HashMap<>();

        //Parent menu
        for(int i=1; i<36;i++) {
            listHeader.add("Step "+i);
        }

        //Generate child menu, counter variable for tracking, and attach it to parent
        List<ListItem> step1 = new ArrayList<>();
        step1.add(new ListItem("Grey Mouse"));
        step1.add(new ListItem("Moskito"));
        step1.add(new ListItem("Arachnee"));
        step1.add(new ListItem("White Gobbly"));
        step1.add(new ListItem("Black Gobbly"));
        step1.add(new ListItem("Evil Tofu"));
        step1.add(new ListItem("Sick Arachnee"));
        step1.add(new ListItem("Blue Piwi"));
        step1.add(new ListItem("Red Piwi"));
        step1.add(new ListItem("Yellow Piwi"));
        step1.add(new ListItem("Green Piwi"));
        step1.add(new ListItem("Purple Piwi"));
        step1.add(new ListItem("Air Spark"));
        step1.add(new ListItem("Water Spark"));
        step1.add(new ListItem("Fire Spark"));
        step1.add(new ListItem("Earth Spark"));
        step1.add(new ListItem("Blue Larva"));
        step1.add(new ListItem("Crab"));
        step1.add(new ListItem("Pink Piwi"));
        step1.add(new ListItem("Tofu"));
        listChild.put(listHeader.get(0), step1);

        List<ListItem> step2 = new ArrayList<>();
        step2.add(new ListItem("Sick Tofu"));
        step2.add(new ListItem("Mush Mush"));
        step2.add(new ListItem("Evil Dandelion"));
        step2.add(new ListItem("Agressive Arachnee"));
        step2.add(new ListItem("One-armed Bandit"));
        step2.add(new ListItem("Demonic Rose"));
        step2.add(new ListItem("Blue Jelly"));
        step2.add(new ListItem("Kwoan"));
        step2.add(new ListItem("Green Larva"));
        step2.add(new ListItem("Gobball"));
        step2.add(new ListItem("Kolerat"));
        step2.add(new ListItem("Mint Jelly"));
        step2.add(new ListItem("Orange Larva"));
        step2.add(new ListItem("Dark Baker"));
        step2.add(new ListItem("Black Tiwabbit"));
        step2.add(new ListItem("Tiwabbit"));
        step2.add(new ListItem("Tiwabbit Wosungwee"));
        step2.add(new ListItem("Wild Sunflower"));
        step2.add(new ListItem("Rogue Clan Bandit"));
        step2.add(new ListItem("Goblin"));
        listChild.put(listHeader.get(1), step2);

        List<ListItem> step3 = new ArrayList<>();
        step3.add(new ListItem("Kokonut"));
        step3.add(new ListItem("Tikoko"));
        step3.add(new ListItem("Glukoko"));
        step3.add(new ListItem("Boar"));
        step3.add(new ListItem("Green Spimush"));
        step3.add(new ListItem("Pumpkwin"));
        step3.add(new ListItem("Vampyre"));
        step3.add(new ListItem("Coco Biblop"));
        step3.add(new ListItem("Morello Cherry Biblop"));
        step3.add(new ListItem("Indigo Biblop"));
        step3.add(new ListItem("Pippin Biblop"));
        step3.add(new ListItem("Karne Rider"));
        step3.add(new ListItem("Sick Grossewer Milirat"));
        step3.add(new ListItem("Mushd"));
        step3.add(new ListItem("Prespic"));
        step3.add(new ListItem("Piglet"));
        step3.add(new ListItem("Yellow Turtle"));
        step3.add(new ListItem("Wabbit"));
        step3.add(new ListItem("Gobbal War Chief"));
        step3.add(new ListItem("Lousy Pig Shepherd"));
        listChild.put(listHeader.get(2), step3);
//
//        List<String> step4 = new ArrayList<String>();
//        step4.add("Whitish Fang");
//        step4.add("Bulbamboo");
//        step4.add("Blue Turtle");
//        step4.add("Crow");
//        step4.add("Starfish Trooper");
//        step4.add("White Snapper");
//        step4.add("Blue Snapper");
//        step4.add("Orange Snapper");
//        step4.add("Green Snapper");
//        step4.add("Bambooto");
//        step4.add("Red Turtle");
//        step4.add("Vampyre Master");
//        step4.add("Minoskito");
//        step4.add("Neye");
//        step4.add("Miliboowolf");
//        step4.add("Ambusher");
//        step4.add("Bulbiflor");
//        step4.add("Green Turtle");
//        step4.add("Bwork Magus");
//        step4.add("Furious Whitish Fang");
//        listChild.put(listHeader.get(3), step4);
//
//        List<String> step5 = new ArrayList<String>();
//        step5.add("Dark Rose");
//        step5.add("Black Wabbit");
//        step5.add("Dark Miner");
//        step5.add("Bwork Archer");
//        step5.add("Blue Spimush");
//        step5.add("Brown Spimush");
//        step5.add("Red Spimush");
//        step5.add("Crackrock");
//        step5.add("Manderisha");
//        step5.add("Kitsou Nakwa");
//        step5.add("Gargoyl");
//        step5.add("Wild Almond Dragoturkey");
//        step5.add("Strawberry Jelly");
//        step5.add("Skeleton Wabbit");
//        step5.add("Invisible Chafer");
//        step5.add("Crackler");
//        step5.add("Bwork");
//        step5.add("Treechnid");
//        step5.add("Khamelrost");
//        int step5counter = 0;
//        int step5max = step5.size();
//        listChild.put(listHeader.get(4), step5);
//
//        List<String> step6 = new ArrayList<String>();
//        step6.add("White Scaraleaf");
//        step6.add("Blue Scaraleaf");
//        step6.add("Red Scaraleaf");
//        step6.add("Green Scaraleaf");
//        step6.add("Rib");
//        step6.add("Coco Blop");
//        step6.add("Morello Cherry Blop");
//        step6.add("Indigo Blop");
//        step6.add("Pippin Blop");
//        step6.add("Farle's Pig");
//        step6.add("Scaratos");
//        step6.add("Boomba");
//        step6.add("Trunknid");
//        step6.add("Chafter Foot Soldier");
//        step6.add("Kloon Snapper");
//        step6.add("Treechnee");
//        step6.add("Chafer");
//        step6.add("Crocodyl");
//        step6.add("Sick Grossewer Rat");
//        step6.add("Scurvion");
//        int step6counter = 0;
//        int step6max = step6.size();
//        listChild.put(listHeader.get(5), step6);
//
//        List<String> step7 = new ArrayList<String>();
//        step7.add("Wild Ginger Dragoturkey");
//        step7.add("Dark Smith");
//        step7.add("Kurasso Craboral");
//        step7.add("Mahlibuh Craboral");
//        step7.add("Mojeeto Craboral");
//        step7.add("Passaoh Craboral");
//        step7.add("Bulbush");
//        step7.add("Major Arachnee");
//        step7.add("Araknawa");
//        step7.add("Gwandpa Wabbit");
//        step7.add("Wo Wabbit");
//        step7.add("Lousy Pig Knight");
//        step7.add("Aboub");
//        step7.add("Amlub");
//        step7.add("Codem");
//        step7.add("Gink");
//        step7.add("Kirevam");
//        step7.add("Let Emoliug");
//        step7.add("Nebgib");
//        step7.add("Nipul");
//        int step7counter = 0;
//        int step7max = step7.size();
//        listChild.put(listHeader.get(6), step7);
//
//        List<String> step8 = new ArrayList<String>();
//        step8.add("Osurc");
//        step8.add("Susej");
//        step8.add("Kokoko");
//        step8.add("Chafer Archer");
//        step8.add("Chafer Lancer");
//        step8.add("Dok Alako");
//        step8.add("Immature Koalak");
//        step8.add("Minokid");
//        step8.add("Grossewer Shaman");
//        step8.add("Boggedown Ouassingue");
//        step8.add("Ouassingal");
//        step8.add("Ouassingue");
//        step8.add("Mahlibuh Palmflower");
//        step8.add("Mojeeto Palmflower");
//        step8.add("Passaoh Palmflower");
//        step8.add("Plain Boar");
//        step8.add("Venerable Treechnid");
//        step8.add("Plain Crackler");
//        step8.add("Pandit");
//        step8.add("Elite Chafter");
//        int step8counter = 0;
//        int step8max = step8.size();
//        listChild.put(listHeader.get(7), step8);
//
//        List<String> step9 = new ArrayList<String>();
//        step9.add("Fungi Master");
//        step9.add("Polished Crackrock");
//        step9.add("Pandikaze");
//        step9.add("Kanniballbo");
//        step9.add("Kanniball Jav");
//        step9.add("Kanniball Sarbak");
//        step9.add("Kanniball Thierry");
//        step9.add("Drunken Pandalette");
//        step9.add("Grossewer Rat");
//        step9.add("Kurasso Palmflower");
//        step9.add("Ouginak");
//        step9.add("Immature White Dreggon");
//        step9.add("Immature Golden Dreggon");
//        step9.add("Immature Black Dreggon");
//        step9.add("Immature Sapphire Dreggon");
//        step9.add("White Dragoss");
//        step9.add("Golden Dragoss");
//        step9.add("Black Dragoss");
//        step9.add("Sapphire Dragoss");
//        step9.add("Leopardo");
//        int step9counter = 0;
//        int step9max = step9.size();
//        listChild.put(listHeader.get(8), step9);
//
//        List<String> step10 = new ArrayList<String>();
//        step10.add("Quetsnakiatl");
//        step10.add("Coralator");
//        step10.add("Kitsou Nae");
//        step10.add("Raul Mops");
//        step10.add("Drunken Pandawa");
//        step10.add("Grass Snake");
//        step10.add("Explosive Shell");
//        step10.add("Cannon Dorf");
//        step10.add("Hazwonarm");
//        step10.add("Blodz Uker");
//        step10.add("White Dreggon");
//        step10.add("Golden Dreggon");
//        step10.add("Black Dreggon");
//        step10.add("Sapphire Dreggon");
//        step10.add("Coco Koalak");
//        step10.add("Morello Cherry Koalak");
//        step10.add("Indigo Koalak");
//        step10.add("Pippin Koalak");
//        step10.add("Hyoactive Rat");
//        step10.add("Sparo");
//        int step10counter = 0;
//        int step10max = step10.size();
//        listChild.put(listHeader.get(9), step10);
//
//        List<String> step11 = new ArrayList<String>();
//        step11.add("Warguerite");
//        step11.add("Apero Ghost");
//        step11.add("Burning Ghost");
//        step11.add("Arepo Ghost");
//        step11.add("Brave Ghost");
//        step11.add("Dorgan Ation");
//        step11.add("Barbrossa");
//        step11.add("Ze Flib");
//        step11.add("Gwass");
//        step11.add("Crocodyl Chief");
//        step11.add("Plissken");
//        step11.add("Kaniger");
//        step11.add("Plain Pikoko");
//        step11.add("Polished Crackler");
//        step11.add("Mufafah");
//        step11.add("Boowolf");
//        step11.add("Pandulum");
//        step11.add("Kido");
//        step11.add("Kilibriss");
//        step11.add("Alert White Dreggon");
//        int step11counter = 0;
//        int step11max = step11.size();
//        listChild.put(listHeader.get(10), step11);
//
//        List<String> step12 = new ArrayList<String>();
//        step12.add("Alert Golden Dreggon");
//        step12.add("Alert Black Dreggon");
//        step12.add("Alert Sapphire Dreggon");
//        step12.add("Dragostess");
//        step12.add("Koalak Warrior");
//        step12.add("Poacher");
//        step12.add("Oni");
//        step12.add("Brown Warko");
//        step12.add("Kitsou Nere");
//        step12.add("Dark Treechnid");
//        step12.add("Bulbig");
//        step12.add("Koalak Gravedigger");
//        step12.add("Koalak Forester");
//        step12.add("Piralak");
//        step12.add("Fisheralak");
//        step12.add("Greedovore");
//        step12.add("Dark Treechnee");
//        step12.add("Wild Golden Dragoturkey");
//        step12.add("Koalak Rider");
//        step12.add("Moopet");
//        int step12counter = 0;
//        int step12max = step12.size();
//        listChild.put(listHeader.get(11), step12);
//
//        List<String> step13 = new ArrayList<String>();
//        step13.add("Holy Bambooto");
//        step13.add("Dreggon Warrior");
//        step13.add("Flying Dreggon");
//        step13.add("Aeroktor the Warrior");
//        step13.add("Aquabralak the Warrior");
//        step13.add("Ignilicrobur the Warrior");
//        step13.add("Terrakubiack the Warrior");
//        step13.add("Zoth Disciple");
//        step13.add("Rotaflor");
//        step13.add("Bloody Koalak");
//        step13.add("Dark Pikoko");
//        step13.add("Floramor");
//        step13.add("Barkritter");
//        step13.add("Wild Koalak");
//        step13.add("Mama Koalak");
//        step13.add("Koalak Mummy");
//        step13.add("Miremop");
//        step13.add("Purple Warko");
//        step13.add("Zoth Girl");
//        step13.add("Zoth Warrior");
//        int step13counter = 0;
//        int step13max = step13.size();
//        listChild.put(listHeader.get(12), step13);
//
//        List<String> step14 = new ArrayList<String>();
//        step14.add("Mopeat");
//        step14.add("Dark Treeckler");
//        step14.add("Alert White Dragoss");
//        step14.add("Alert Golden Dragoss");
//        step14.add("Alert Black Dragoss");
//        step14.add("Alert Sapphire Dragoss");
//        step14.add("Soryo Firefoux");
//        step14.add("Kitsou Nufeu");
//        step14.add("Maho Firefoux");
//        step14.add("Drakoalak");
//        step14.add("Reapalak");
//        step14.add("Koalak Master");
//        step14.add("Cheeken");
//        step14.add("Ignirkocropos the Famished");
//        step14.add("Terraburkahl the Perdifious");
//        step14.add("Aerogoburius the Malicious");
//        step14.add("Aqualikros the Merciless");
//        step14.add("Trool");
//        int step14counter = 0;
//        int step14max = step14.size();
//        listChild.put(listHeader.get(13), step14);
//
//        List<String> step15 = new ArrayList<String>();
//        step15.add(" Zoth Sergeant");
//        step15.add("Snailmet");
//        step15.add("Air Pikoko");
//        step15.add("Light Treeckler");
//        step15.add("Zoth Master");
//        step15.add("Mushnid");
//        step15.add("Yokai Firefoux ");
//        step15.add("Trumperelle");
//        step15.add("Mushmunch");
//        step15.add("Leopardo Ghost");
//        step15.add("Pandikaze Ghost");
//        step15.add("Mush Tup");
//        step15.add("Mush Rhume");
//        step15.add("Mush Mish");
//        step15.add("Pandulum Ghost");
//        step15.add("Soryo Firefoux Ghost");
//        step15.add("Pandora");
//        step15.add("Mopy King");
//        step15.add("Maho Firefoux Ghost");
//        step15.add("Yokai Firefoux Ghost");
//        int step15counter = 0;
//        int step15max = step15.size();
//        listChild.put(listHeader.get(14), step15);
//
//        List<String> step16 = new ArrayList<String>();
//        step16.add("Pandora Ghost");
//        step16.add("Tanukouï San Ghost");
//        int step16counter = 0;
//        int step16max = step16.size();
//        listChild.put(listHeader.get(15), step16);
//
//        List<String> step17 = new ArrayList<String>();
//        step17.add("Sponge Mob");
//        step17.add("Royal Gobball");
//        step17.add("Famished Sunflower");
//        step17.add("Royal Blue Jelly");
//        step17.add("Shin Larva");
//        step17.add("GM Wabbit");
//        step17.add("Golden Scarabugly");
//        step17.add("Bworkette");
//        step17.add("Dragon Pig");
//        step17.add("Mumminotor");
//        step17.add("Deminoball");
//        step17.add("Royal Mint Jelly");
//        step17.add("Royal Tofu");
//        step17.add("Royal Coco Blop");
//        step17.add("Royal Morello Cherry Blop");
//        step17.add("Royal Indigo Blop");
//        step17.add("Royal Pippin Blop");
//        step17.add("Black Rat");
//        step17.add("White Rat");
//        step17.add("Minotoror");
//        int step17counter = 0;
//        int step17max = step17.size();
//        listChild.put(listHeader.get(16), step17);
//
//        List<String> step18 = new ArrayList<String>();
//        step18.add("Hell Mina");
//        step18.add("Tanukouï San");
//        step18.add("Legendary Crackler");
//        step18.add("Sewer Keeper");
//        step18.add("Royal Strawberry Jelly");
//        step18.add("Lord Crow");
//        step18.add("Royal Rainbow Blop");
//        step18.add("Great Coralator");
//        step18.add("Gourlo the Terrible");
//        step18.add("Moowolf");
//        step18.add("Wa Wabbit");
//        step18.add("Moon");
//        step18.add("Koolich");
//        step18.add("Skeunk");
//        step18.add("Ancestral Treechnid");
//        step18.add("Pandora Master");
//        step18.add("Stunned Tynril");
//        step18.add("Dismayed Tynril");
//        step18.add("Disconcerted Tynril");
//        step18.add("Perfidious Tynril");
//        int step18counter = 0;
//        int step18max = step18.size();
//        listChild.put(listHeader.get(17), step18);

//        List<String> step19 = new ArrayList<String>();
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        step19.add("");
//        int step19counter = 0;
//        int step19max = step19.size();

//       List<String> step20 = new ArrayList<String>();
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        step2.add("");
//        int step2counter = 0;
//        int step2max = step2.size();
//        listChild.put(listHeader.get(2), step2);

//       List<String> step3 = new ArrayList<String>();
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        step3.add("");
//        int step3counter = 0;
//        int step3max = step3.size();
//        listChild.put(listHeader.get(3), step3);


    }

}
