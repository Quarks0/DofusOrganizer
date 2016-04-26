package com.quarks0.dofusorganizer;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HarvestTracker extends AppCompatActivity {

    ExpandableListAdapter eHListAdapter;
    ExpandableListView eHListView;
    ArrayList<String> listHeader;
    HashMap<String,List<ListItem>> listChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create a view, initialize the list, push contents to adapter
        eHListView = (ExpandableListView) findViewById(R.id.eternalHarvestList);

        if(savedInstanceState != null){
            listChild = (HashMap<String, List<ListItem>>) savedInstanceState.getSerializable("EHmap");
            listHeader = savedInstanceState.getStringArrayList("EHheader");
        }
        else{
            initiateList();
        }

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

        //Toggle green/white on click
        eHListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ListItem tempItem = eHListAdapter.getChild(groupPosition,childPosition);
                boolean toggleresult = tempItem.itemToggle();

                eHListAdapter.notifyDataSetChanged();

                if (toggleresult) {
                    Toast.makeText(getApplicationContext(), "Get!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
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

    @Override
    public void onPause(){ //Saved information (usually upon quit or kill)
        super.onPause();

        FileOutputStream fileOutputStream;

        try{
            fileOutputStream = openFileOutput("EHhashmap.dat",Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
            outputStream.writeObject(listHeader);
            outputStream.writeObject(listChild);
            outputStream.close();
        }
        catch (IOException e){
            Toast.makeText(getApplicationContext(), "IOException: cannot write EHhashmap", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume(){ //Restores from file, or savedInstanceState if available
        super.onResume();

        FileInputStream fileInputStream;

        try {
            fileInputStream = openFileInput("EHhashmap.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(fileInputStream));
            listHeader = (ArrayList<String>) objectInputStream.readObject();
            listChild = (HashMap<String, List<ListItem>>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "IOException: cannot read EHhashmap", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "ClassNotFoundException: cannot read listHeader or listChild", Toast.LENGTH_SHORT).show();
        }
        eHListAdapter = new ExpandableListAdapter(this, listHeader,listChild);
        eHListView.setAdapter(eHListAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        //Hashmaps have to be converted to serializables before being added
        savedInstanceState.putSerializable("EHmap", listChild);
        savedInstanceState.putStringArrayList("EHheader", listHeader);
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

        List<ListItem> step4 = new ArrayList<>();
        step4.add(new ListItem("Whitish Fang"));
        step4.add(new ListItem("Bulbamboo"));
        step4.add(new ListItem("Blue Turtle"));
        step4.add(new ListItem("Crow"));
        step4.add(new ListItem("Starfish Trooper"));
        step4.add(new ListItem("White Snapper"));
        step4.add(new ListItem("Blue Snapper"));
        step4.add(new ListItem("Orange Snapper"));
        step4.add(new ListItem("Green Snapper"));
        step4.add(new ListItem("Bambooto"));
        step4.add(new ListItem("Red Turtle"));
        step4.add(new ListItem("Vampyre Master"));
        step4.add(new ListItem("Minoskito"));
        step4.add(new ListItem("Neye"));
        step4.add(new ListItem("Miliboowolf"));
        step4.add(new ListItem("Ambusher"));
        step4.add(new ListItem("Bulbiflor"));
        step4.add(new ListItem("Green Turtle"));
        step4.add(new ListItem("Bwork Magus"));
        step4.add(new ListItem("Furious Whitish Fang"));
        listChild.put(listHeader.get(3), step4);

        List<ListItem> step5 = new ArrayList<>();
        step5.add(new ListItem("Dark Rose"));
        step5.add(new ListItem("Black Wabbit"));
        step5.add(new ListItem("Dark Miner"));
        step5.add(new ListItem("Bwork Archer"));
        step5.add(new ListItem("Blue Spimush"));
        step5.add(new ListItem("Brown Spimush"));
        step5.add(new ListItem("Red Spimush"));
        step5.add(new ListItem("Crackrock"));
        step5.add(new ListItem("Manderisha"));
        step5.add(new ListItem("Kitsou Nakwa"));
        step5.add(new ListItem("Gargoyl"));
        step5.add(new ListItem("Wild Almond Dragoturkey"));
        step5.add(new ListItem("Strawberry Jelly"));
        step5.add(new ListItem("Skeleton Wabbit"));
        step5.add(new ListItem("Invisible Chafer"));
        step5.add(new ListItem("Crackler"));
        step5.add(new ListItem("Bwork"));
        step5.add(new ListItem("Treechnid"));
        step5.add(new ListItem("Khamelrost"));
        listChild.put(listHeader.get(4), step5);

        List<ListItem> step6 = new ArrayList<>();
        step6.add(new ListItem("White Scaraleaf"));
        step6.add(new ListItem("Blue Scaraleaf"));
        step6.add(new ListItem("Red Scaraleaf"));
        step6.add(new ListItem("Green Scaraleaf"));
        step6.add(new ListItem("Rib"));
        step6.add(new ListItem("Coco Blop"));
        step6.add(new ListItem("Morello Cherry Blop"));
        step6.add(new ListItem("Indigo Blop"));
        step6.add(new ListItem("Pippin Blop"));
        step6.add(new ListItem("Farle's Pig"));
        step6.add(new ListItem("Scaratos"));
        step6.add(new ListItem("Boomba"));
        step6.add(new ListItem("Trunknid"));
        step6.add(new ListItem("Chafter Foot Soldier"));
        step6.add(new ListItem("Kloon Snapper"));
        step6.add(new ListItem("Treechnee"));
        step6.add(new ListItem("Chafer"));
        step6.add(new ListItem("Crocodyl"));
        step6.add(new ListItem("Sick Grossewer Rat"));
        step6.add(new ListItem("Scurvion"));
        listChild.put(listHeader.get(5), step6);

        List<ListItem> step7 = new ArrayList<>();
        step7.add(new ListItem("Wild Ginger Dragoturkey"));
        step7.add(new ListItem("Dark Smith"));
        step7.add(new ListItem("Kurasso Craboral"));
        step7.add(new ListItem("Mahlibuh Craboral"));
        step7.add(new ListItem("Mojeeto Craboral"));
        step7.add(new ListItem("Passaoh Craboral"));
        step7.add(new ListItem("Bulbush"));
        step7.add(new ListItem("Major Arachnee"));
        step7.add(new ListItem("Araknawa"));
        step7.add(new ListItem("Gwandpa Wabbit"));
        step7.add(new ListItem("Wo Wabbit"));
        step7.add(new ListItem("Lousy Pig Knight"));
        step7.add(new ListItem("Aboub"));
        step7.add(new ListItem("Amlub"));
        step7.add(new ListItem("Codem"));
        step7.add(new ListItem("Gink"));
        step7.add(new ListItem("Kirevam"));
        step7.add(new ListItem("Let Emoliug"));
        step7.add(new ListItem("Nebgib"));
        step7.add(new ListItem("Nipul"));
        listChild.put(listHeader.get(6), step7);

        List<ListItem> step8 = new ArrayList<>();
        step8.add(new ListItem("Osurc"));
        step8.add(new ListItem("Susej"));
        step8.add(new ListItem("Kokoko"));
        step8.add(new ListItem("Chafer Archer"));
        step8.add(new ListItem("Chafer Lancer"));
        step8.add(new ListItem("Dok Alako"));
        step8.add(new ListItem("Immature Koalak"));
        step8.add(new ListItem("Minokid"));
        step8.add(new ListItem("Grossewer Shaman"));
        step8.add(new ListItem("Boggedown Ouassingue"));
        step8.add(new ListItem("Ouassingal"));
        step8.add(new ListItem("Ouassingue"));
        step8.add(new ListItem("Mahlibuh Palmflower"));
        step8.add(new ListItem("Mojeeto Palmflower"));
        step8.add(new ListItem("Passaoh Palmflower"));
        step8.add(new ListItem("Plain Boar"));
        step8.add(new ListItem("Venerable Treechnid"));
        step8.add(new ListItem("Plain Crackler"));
        step8.add(new ListItem("Pandit"));
        step8.add(new ListItem("Elite Chafter"));
        listChild.put(listHeader.get(7), step8);

        List<ListItem> step9 = new ArrayList<>();
        step9.add(new ListItem("Fungi Master"));
        step9.add(new ListItem("Polished Crackrock"));
        step9.add(new ListItem("Pandikaze"));
        step9.add(new ListItem("Kanniballbo"));
        step9.add(new ListItem("Kanniball Jav"));
        step9.add(new ListItem("Kanniball Sarbak"));
        step9.add(new ListItem("Kanniball Thierry"));
        step9.add(new ListItem("Drunken Pandalette"));
        step9.add(new ListItem("Grossewer Rat"));
        step9.add(new ListItem("Kurasso Palmflower"));
        step9.add(new ListItem("Ouginak"));
        step9.add(new ListItem("Immature White Dreggon"));
        step9.add(new ListItem("Immature Golden Dreggon"));
        step9.add(new ListItem("Immature Black Dreggon"));
        step9.add(new ListItem("Immature Sapphire Dreggon"));
        step9.add(new ListItem("White Dragoss"));
        step9.add(new ListItem("Golden Dragoss"));
        step9.add(new ListItem("Black Dragoss"));
        step9.add(new ListItem("Sapphire Dragoss"));
        step9.add(new ListItem("Leopardo"));
        listChild.put(listHeader.get(8), step9);

        List<ListItem> step10 = new ArrayList<>();
        step10.add(new ListItem("Quetsnakiatl"));
        step10.add(new ListItem("Coralator"));
        step10.add(new ListItem("Kitsou Nae"));
        step10.add(new ListItem("Raul Mops"));
        step10.add(new ListItem("Drunken Pandawa"));
        step10.add(new ListItem("Grass Snake"));
        step10.add(new ListItem("Explosive Shell"));
        step10.add(new ListItem("Cannon Dorf"));
        step10.add(new ListItem("Hazwonarm"));
        step10.add(new ListItem("Blodz Uker"));
        step10.add(new ListItem("White Dreggon"));
        step10.add(new ListItem("Golden Dreggon"));
        step10.add(new ListItem("Black Dreggon"));
        step10.add(new ListItem("Sapphire Dreggon"));
        step10.add(new ListItem("Coco Koalak"));
        step10.add(new ListItem("Morello Cherry Koalak"));
        step10.add(new ListItem("Indigo Koalak"));
        step10.add(new ListItem("Pippin Koalak"));
        step10.add(new ListItem("Hyoactive Rat"));
        step10.add(new ListItem("Sparo"));
        listChild.put(listHeader.get(9), step10);

        List<ListItem> step11 = new ArrayList<>();
        step11.add(new ListItem("Warguerite"));
        step11.add(new ListItem("Apero Ghost"));
        step11.add(new ListItem("Burning Ghost"));
        step11.add(new ListItem("Arepo Ghost"));
        step11.add(new ListItem("Brave Ghost"));
        step11.add(new ListItem("Dorgan Ation"));
        step11.add(new ListItem("Barbrossa"));
        step11.add(new ListItem("Ze Flib"));
        step11.add(new ListItem("Gwass"));
        step11.add(new ListItem("Crocodyl Chief"));
        step11.add(new ListItem("Plissken"));
        step11.add(new ListItem("Kaniger"));
        step11.add(new ListItem("Plain Pikoko"));
        step11.add(new ListItem("Polished Crackler"));
        step11.add(new ListItem("Mufafah"));
        step11.add(new ListItem("Boowolf"));
        step11.add(new ListItem("Pandulum"));
        step11.add(new ListItem("Kido"));
        step11.add(new ListItem("Kilibriss"));
        step11.add(new ListItem("Alert White Dreggon"));
        listChild.put(listHeader.get(10), step11);

        List<ListItem> step12 = new ArrayList<>();
        step12.add(new ListItem("Alert Golden Dreggon"));
        step12.add(new ListItem("Alert Black Dreggon"));
        step12.add(new ListItem("Alert Sapphire Dreggon"));
        step12.add(new ListItem("Dragostess"));
        step12.add(new ListItem("Koalak Warrior"));
        step12.add(new ListItem("Poacher"));
        step12.add(new ListItem("Oni"));
        step12.add(new ListItem("Brown Warko"));
        step12.add(new ListItem("Kitsou Nere"));
        step12.add(new ListItem("Dark Treechnid"));
        step12.add(new ListItem("Bulbig"));
        step12.add(new ListItem("Koalak Gravedigger"));
        step12.add(new ListItem("Koalak Forester"));
        step12.add(new ListItem("Piralak"));
        step12.add(new ListItem("Fisheralak"));
        step12.add(new ListItem("Greedovore"));
        step12.add(new ListItem("Dark Treechnee"));
        step12.add(new ListItem("Wild Golden Dragoturkey"));
        step12.add(new ListItem("Koalak Rider"));
        step12.add(new ListItem("Moopet"));
        listChild.put(listHeader.get(11), step12);

        List<ListItem> step13 = new ArrayList<>();
        step13.add(new ListItem("Holy Bambooto"));
        step13.add(new ListItem("Dreggon Warrior"));
        step13.add(new ListItem("Flying Dreggon"));
        step13.add(new ListItem("Aeroktor the Warrior"));
        step13.add(new ListItem("Aquabralak the Warrior"));
        step13.add(new ListItem("Ignilicrobur the Warrior"));
        step13.add(new ListItem("Terrakubiack the Warrior"));
        step13.add(new ListItem("Zoth Disciple"));
        step13.add(new ListItem("Rotaflor"));
        step13.add(new ListItem("Bloody Koalak"));
        step13.add(new ListItem("Dark Pikoko"));
        step13.add(new ListItem("Floramor"));
        step13.add(new ListItem("Barkritter"));
        step13.add(new ListItem("Wild Koalak"));
        step13.add(new ListItem("Mama Koalak"));
        step13.add(new ListItem("Koalak Mummy"));
        step13.add(new ListItem("Miremop"));
        step13.add(new ListItem("Purple Warko"));
        step13.add(new ListItem("Zoth Girl"));
        step13.add(new ListItem("Zoth Warrior"));
        listChild.put(listHeader.get(12), step13);

        List<ListItem> step14 = new ArrayList<>();
        step14.add(new ListItem("Mopeat"));
        step14.add(new ListItem("Dark Treeckler"));
        step14.add(new ListItem("Alert White Dragoss"));
        step14.add(new ListItem("Alert Golden Dragoss"));
        step14.add(new ListItem("Alert Black Dragoss"));
        step14.add(new ListItem("Alert Sapphire Dragoss"));
        step14.add(new ListItem("Soryo Firefoux"));
        step14.add(new ListItem("Kitsou Nufeu"));
        step14.add(new ListItem("Maho Firefoux"));
        step14.add(new ListItem("Drakoalak"));
        step14.add(new ListItem("Reapalak"));
        step14.add(new ListItem("Koalak Master"));
        step14.add(new ListItem("Cheeken"));
        step14.add(new ListItem("Ignirkocropos the Famished"));
        step14.add(new ListItem("Terraburkahl the Perdifious"));
        step14.add(new ListItem("Aerogoburius the Malicious"));
        step14.add(new ListItem("Aqualikros the Merciless"));
        step14.add(new ListItem("Trool"));
        listChild.put(listHeader.get(13), step14);

        List<ListItem> step15 = new ArrayList<>();
        step15.add(new ListItem("Zoth Sergeant"));
        step15.add(new ListItem("Snailmet"));
        step15.add(new ListItem("Air Pikoko"));
        step15.add(new ListItem("Light Treeckler"));
        step15.add(new ListItem("Zoth Master"));
        step15.add(new ListItem("Mushnid"));
        step15.add(new ListItem("Yokai Firefoux"));
        step15.add(new ListItem("Trumperelle"));
        step15.add(new ListItem("Mushmunch"));
        step15.add(new ListItem("Leopardo Ghost"));
        step15.add(new ListItem("Pandikaze Ghost"));
        step15.add(new ListItem("Mush Tup"));
        step15.add(new ListItem("Mush Rhume"));
        step15.add(new ListItem("Mush Mish"));
        step15.add(new ListItem("Pandulum Ghost"));
        step15.add(new ListItem("Soryo Firefoux Ghost"));
        step15.add(new ListItem("Pandora"));
        step15.add(new ListItem("Mopy King"));
        step15.add(new ListItem("Maho Firefoux Ghost"));
        step15.add(new ListItem("Yokai Firefoux Ghost"));
        listChild.put(listHeader.get(14), step15);

        List<ListItem> step16 = new ArrayList<>();
        step16.add(new ListItem("Pandora Ghost"));
        step16.add(new ListItem("Tanukouï San Ghost"));
        listChild.put(listHeader.get(15), step16);

        List<ListItem> step17 = new ArrayList<>();
        step17.add(new ListItem("Sponge Mob"));
        step17.add(new ListItem("Royal Gobball"));
        step17.add(new ListItem("Famished Sunflower"));
        step17.add(new ListItem("Royal Blue Jelly"));
        step17.add(new ListItem("Shin Larva"));
        step17.add(new ListItem("GM Wabbit"));
        step17.add(new ListItem("Golden Scarabugly"));
        step17.add(new ListItem("Bworkette"));
        step17.add(new ListItem("Dragon Pig"));
        step17.add(new ListItem("Mumminotor"));
        step17.add(new ListItem("Deminoball"));
        step17.add(new ListItem("Royal Mint Jelly"));
        step17.add(new ListItem("Royal Tofu"));
        step17.add(new ListItem("Royal Coco Blop"));
        step17.add(new ListItem("Royal Morello Cherry Blop"));
        step17.add(new ListItem("Royal Indigo Blop"));
        step17.add(new ListItem("Royal Pippin Blop"));
        step17.add(new ListItem("Black Rat"));
        step17.add(new ListItem("White Rat"));
        step17.add(new ListItem("Minotoror"));
        listChild.put(listHeader.get(16), step17);

        List<ListItem> step18 = new ArrayList<>();
        step18.add(new ListItem("Hell Mina"));
        step18.add(new ListItem("Tanukouï San"));
        step18.add(new ListItem("Legendary Crackler"));
        step18.add(new ListItem("Sewer Keeper"));
        step18.add(new ListItem("Royal Strawberry Jelly"));
        step18.add(new ListItem("Lord Crow"));
        step18.add(new ListItem("Royal Rainbow Blop"));
        step18.add(new ListItem("Great Coralator"));
        step18.add(new ListItem("Gourlo the Terrible"));
        step18.add(new ListItem("Moowolf"));
        step18.add(new ListItem("Wa Wabbit"));
        step18.add(new ListItem("Moon"));
        step18.add(new ListItem("Koolich"));
        step18.add(new ListItem("Skeunk"));
        step18.add(new ListItem("Ancestral Treechnid"));
        step18.add(new ListItem("Pandora Master"));
        step18.add(new ListItem("Stunned Tynril"));
        step18.add(new ListItem("Dismayed Tynril"));
        step18.add(new ListItem("Disconcerted Tynril"));
        step18.add(new ListItem("Perfidious Tynril"));
        listChild.put(listHeader.get(17), step18);

        List<ListItem> step19 = new ArrayList<>();
        step19.add(new ListItem("Royal Lemon Jelly"));
        step19.add(new ListItem("Touchparak"));
        step19.add(new ListItem("Bworker"));
        step19.add(new ListItem("Sphincter Cell"));
        step19.add(new ListItem("Minotot"));
        step19.add(new ListItem("Silf the Greater Bherb"));
        step19.add(new ListItem("Crocabulia"));
        step19.add(new ListItem("Peki Peki"));
        step19.add(new ListItem("Soft Oak"));
        step19.add(new ListItem("Ougaa"));
        step19.add(new ListItem("Kimbo"));
        listChild.put(listHeader.get(18), step19);

       List<ListItem> step20 = new ArrayList<>();
        step20.add(new ListItem("Arachnekros the Aggressive"));
        step20.add(new ListItem("Arakula the Carpature"));
        step20.add(new ListItem("Gobballyhoo the Noisy"));
        step20.add(new ListItem("Larvalaska the Cold"));
        step20.add(new ListItem("Piwiki the Witty"));
        step20.add(new ListItem("Krabaoly the Patient"));
        step20.add(new ListItem("Dandel the Boy"));
        step20.add(new ListItem("Tofulsom the Jailer"));
        step20.add(new ListItem("Piwiliam the Brave"));
        step20.add(new ListItem("Famouse the Little-Known"));
        step20.add(new ListItem("Moskoitus the Interruptor"));
        step20.add(new ListItem("Matmushmush the Flasher"));
        step20.add(new ListItem("Piwi the Ermine"));
        step20.add(new ListItem("Piwinston the Churlish"));
        step20.add(new ListItem("Piwilde the Bossie"));
        step20.add(new ListItem("Arachnangel the Hopeful"));
        step20.add(new ListItem("Tofull the Optimist"));
        step20.add(new ListItem("Tofudd the Hunter"));
        step20.add(new ListItem("Gobbach the Contrapuntaler"));
        step20.add(new ListItem("Piwicker the Manly"));
        listChild.put(listHeader.get(19), step20);

        List<ListItem> step21 = new ArrayList<>();
        step21.add(new ListItem("Tiwana the Tokin'"));
        step21.add(new ListItem("Jellvis the King"));
        step21.add(new ListItem("Bakeraider the Tomb"));
        step21.add(new ListItem("Roseanne the Yanker"));
        step21.add(new ListItem("Glukoko the Slow"));
        step21.add(new ListItem("Gobballad the Romantic"));
        step21.add(new ListItem("Goblimp the Bis Kit"));
        step21.add(new ListItem("Larvadelaide the Ozie"));
        step21.add(new ListItem("Kokonan the Talker"));
        step21.add(new ListItem("Koleraspootin the Anesthesialogist"));
        step21.add(new ListItem("Kwoanium the Smart"));
        step21.add(new ListItem("Jelleno the Chinny"));
        step21.add(new ListItem("Bandirty the Messy"));
        step21.add(new ListItem("Larvalencia the Orange"));
        step21.add(new ListItem("Serpico the Honest"));
        step21.add(new ListItem("Bandinamit the Explosive"));
        step21.add(new ListItem("Eskoko the Baron"));
        step21.add(new ListItem("Tiwaldo the Hidden"));
        step21.add(new ListItem("Tiwascal the Wapper"));
        step21.add(new ListItem("Hunflower the Sinful"));
        listChild.put(listHeader.get(20), step21);

        List<ListItem> step22 = new ArrayList<>();
        step22.add(new ListItem("Turtan'ernie the Streetwise"));
        step22.add(new ListItem("Boarnigen the Damasker"));
        step22.add(new ListItem("Bulbamoon the Trumpeter"));
        step22.add(new ListItem("Biblopopo the Organiser"));
        step22.add(new ListItem("Gobbalky the Stubborn"));
        step22.add(new ListItem("Spimushtache the Hairy"));
        step22.add(new ListItem("Billbiblop the Great"));
        step22.add(new ListItem("Karnyona the Rider"));
        step22.add(new ListItem("Pigstol the Sexy"));
        step22.add(new ListItem("Biblokajin the Bald"));
        step22.add(new ListItem("Mushdrill the Piercer"));
        step22.add(new ListItem("Jackellington the Lantewn"));
        step22.add(new ListItem("Pigoblet the Useful"));
        step22.add(new ListItem("Bibloponey the Entertainer"));
        step22.add(new ListItem("Prestreet the Fighter"));
        step22.add(new ListItem("Miliopold the Bloomer"));
        step22.add(new ListItem("Vamp the Impalest"));
        step22.add(new ListItem("McWhabbit the Diehard"));
        step22.add(new ListItem("Snowhitisha the Pure"));
        step22.add(new ListItem("Turture the Hooded"));
        listChild.put(listHeader.get(21), step22);

        List<ListItem> step23 = new ArrayList<>();
        step23.add(new ListItem("Ambushapens the Unlucky"));
        step23.add(new ListItem("Bambottinit the Quiet"));
        step23.add(new ListItem("Wabbin the Wich"));
        step23.add(new ListItem("Snappu the Shopkeep"));
        step23.add(new ListItem("Bulbisonic the Penetrating"));
        step23.add(new ListItem("Blorko the Colourful"));
        step23.add(new ListItem("Bworkoder the Mazter"));
        step23.add(new ListItem("Crowmanion the Primitive"));
        step23.add(new ListItem("Minoskittle the Coloured"));
        step23.add(new ListItem("Eyemi the Narcissist"));
        step23.add(new ListItem("Lupisnockio the Woodwolf"));
        step23.add(new ListItem("Snappy the Fishfrier"));
        step23.add(new ListItem("Turtrenalds the Tragic"));
        step23.add(new ListItem("Milivanilli the Mime"));
        step23.add(new ListItem("Milikkybum the Informer"));
        step23.add(new ListItem("Zorrose the Messican"));
        step23.add(new ListItem("Snapp the Dragon"));
        step23.add(new ListItem("Turticorn the Horned"));
        step23.add(new ListItem("Lord Lacedhat the Vampyric"));
        step23.add(new ListItem("Snapple the Wise"));
        listChild.put(listHeader.get(22), step23);

        List<ListItem> step24 = new ArrayList<>();
        step24.add(new ListItem("Scarahazad the Storyteller"));
        step24.add(new ListItem("Spimushty the Smelly"));
        step24.add(new ListItem("Speedmush the Racer"));
        step24.add(new ListItem("Bworak the Bohemian"));
        step24.add(new ListItem("Jiminicrackler the Conscious"));
        step24.add(new ListItem("Crackrockisree the Tiger"));
        step24.add(new ListItem("Gargoyla the Paranoiac"));
        step24.add(new ListItem("Scaramel the Melty"));
        step24.add(new ListItem("Chafaldrag the Charming"));
        step24.add(new ListItem("Khameleltux the Tolerant"));
        step24.add(new ListItem("Kitsewey the Blue"));
        step24.add(new ListItem("Salamaa the Henpeck"));
        step24.add(new ListItem("Scarabreef the Short"));
        step24.add(new ListItem("Spimushuaia the Traveller"));
        step24.add(new ListItem("Rib the Torn"));
        step24.add(new ListItem("Wabbitor the Apt"));
        step24.add(new ListItem("Jellyposukshion the Slim"));
        step24.add(new ListItem("Treekniddio the Needy"));
        step24.add(new ListItem("Scaratheef the Pincher"));
        step24.add(new ListItem("Dragotitis the Painful"));
        listChild.put(listHeader.get(23), step24);

        List<ListItem> step25 = new ArrayList<>();
        step25.add(new ListItem("Boombora the Dangerous"));
        step25.add(new ListItem("Bulbushisu the Makisan"));
        step25.add(new ListItem("Chaferanho the Essential"));
        step25.add(new ListItem("Chafred the Fish"));
        step25.add(new ListItem("Blopulent the Pretentious"));
        step25.add(new ListItem("Croccyx the Bummer"));
        step25.add(new ListItem("Pighatchoo the Electrical"));
        step25.add(new ListItem("Blopium the Delirious"));
        step25.add(new ListItem("Snappster the Sued"));
        step25.add(new ListItem("Crabartanian the Allforone"));
        step25.add(new ListItem("Craborthos the All"));
        step25.add(new ListItem("Crabaramis the One"));
        step25.add(new ListItem("Blorchid the Gorgeous"));
        step25.add(new ListItem("Blopal the Precious"));
        step25.add(new ListItem("Scaraheath the Hanger"));
        step25.add(new ListItem("Scorbison the Lonely"));
        step25.add(new ListItem("Ratatouille the Stirrer"));
        step25.add(new ListItem("Treeknidylus"));
        step25.add(new ListItem("Trunkbeard the Gentle"));
        step25.add(new ListItem("Drakokidoki the Volunteer"));
        listChild.put(listHeader.get(24), step25);

        List<ListItem> step26 = new ArrayList<>();
        step26.add(new ListItem("Abounteous the Generous"));
        step26.add(new ListItem("Amlullabeye the Dreamer"));
        step26.add(new ListItem("Arachnawar the Killinmachin"));
        step26.add(new ListItem("Chaferotix the Sixtininth"));
        step26.add(new ListItem("Chafermented the Drinker"));
        step26.add(new ListItem("Codemonic the Mean"));
        step26.add(new ListItem("Smitherz the Licker"));
        step26.add(new ListItem("Ginsync the Hyperactive"));
        step26.add(new ListItem("Kirevampiro the Wrestler"));
        step26.add(new ListItem("Misskokoko the Channel"));
        step26.add(new ListItem("Lert Macraken the Used Emo"));
        step26.add(new ListItem("Pygknightlion the Lousy"));
        step26.add(new ListItem("Arachma the Greek"));
        step26.add(new ListItem("Nebuchadnezzar the Conqueror"));
        step26.add(new ListItem("Niptuk the Plasticynic"));
        step26.add(new ListItem("Osurcus the Tamer"));
        step26.add(new ListItem("Crabathos the For"));
        step26.add(new ListItem("Suzessman the Enthusiastic"));
        step26.add(new ListItem("Gwabbit the Wunner"));
        step26.add(new ListItem("Wowalker the Egyptian"));
        listChild.put(listHeader.get(25), step26);

        List<ListItem> step27 = new ArrayList<>();
        step27.add(new ListItem("Ouassingiam the Tyrant"));
        step27.add(new ListItem("Dokterwho the Tardisporter"));
        step27.add(new ListItem("Fung Ku the Master"));
        step27.add(new ListItem("Shamassel the Off"));
        step27.add(new ListItem("Koaldmen the Grumpy"));
        step27.add(new ListItem("Kannimantha the Maneater"));
        step27.add(new ListItem("Kanniranda the Maniac"));
        step27.add(new ListItem("Palmpilot the Yuppie"));
        step27.add(new ListItem("Chaferuption the Volcanic"));
        step27.add(new ListItem("Minoknok the Visitor"));
        step27.add(new ListItem("Palmoleaf the Greasy"));
        step27.add(new ListItem("Ouassup the Irritating"));
        step27.add(new ListItem("Ougineemo the Lost"));
        step27.add(new ListItem("Pandaltry the Unknown"));
        step27.add(new ListItem("Pandartmoore the Dogged"));
        step27.add(new ListItem("Naypalm the Herbivorous"));
        step27.add(new ListItem("Boarealis the Bright"));
        step27.add(new ListItem("Cracklerod the Old"));
        step27.add(new ListItem("Crackrodilrock the Helltune"));
        step27.add(new ListItem("Treektamak the Loud"));
        listChild.put(listHeader.get(26), step27);

        List<ListItem> step28 = new ArrayList<>();
        step28.add(new ListItem("Dragospel the Black"));
        step28.add(new ListItem("Kojaklator the Lollipoper"));
        step28.add(new ListItem("Pandarwin the Naturist"));
        step28.add(new ListItem("Pandan the Desperate"));
        step28.add(new ListItem("Dragostino the Tiny"));
        step28.add(new ListItem("Rattle the Hummer"));
        step28.add(new ListItem("Dragoolash the Stewed"));
        step28.add(new ListItem("Drakween the Cross Dresser"));
        step28.add(new ListItem("Dreggershween the Tinpanalley"));
        step28.add(new ListItem("Dreggonzola the Cheesy"));
        step28.add(new ListItem("Kannemik the Skinny"));
        step28.add(new ListItem("Kannarrie the Reckless"));
        step28.add(new ListItem("Kitchy the Scratcher"));
        step28.add(new ListItem("Palmella the Hefty"));
        step28.add(new ListItem("Leopardon the Sorry"));
        step28.add(new ListItem("Ougathard the Fortunate"));
        step28.add(new ListItem("Quetnin the Fictional"));
        step28.add(new ListItem("Raul Modrid the Chulo"));
        step28.add(new ListItem("Dragoskovit the Barefoot"));
        step28.add(new ListItem("Dragaustin the Power"));
        listChild.put(listHeader.get(27), step28);

        List<ListItem> step29 = new ArrayList<>();
        step29.add(new ListItem("Arepotair the Bespectacled"));
        step29.add(new ListItem("Aperobics the Dynamic"));
        step29.add(new ListItem("Barbrosskam the Chief"));
        step29.add(new ListItem("Dreggommomm the Chewer"));
        step29.add(new ListItem("Ghostabrava the Tourist"));
        step29.add(new ListItem("Polterghaisk the Stray Soul"));
        step29.add(new ListItem("Ganon the Dwarf"));
        step29.add(new ListItem("Jackoalak the Ripper"));
        step29.add(new ListItem("Dreggump the Shrimp"));
        step29.add(new ListItem("Grasnakizanami the Ruler"));
        step29.add(new ListItem("Supergwass the Free"));
        step29.add(new ListItem("Hazwonball the Hickler"));
        step29.add(new ListItem("Ratilla the Hun"));
        step29.add(new ListItem("Crackoalak the Blonde"));
        step29.add(new ListItem("Koaly the Fiddler"));
        step29.add(new ListItem("Snapoalak the Redhead"));
        step29.add(new ListItem("Dreggatón the Latino"));
        step29.add(new ListItem("Sparodi the Python"));
        step29.add(new ListItem("Warazpacho the Cherrilla"));
        step29.add(new ListItem("Dragamemnon the Deadtroyer"));
        listChild.put(listHeader.get(28), step29);

       List<ListItem> step30 = new ArrayList<>();
        step30.add(new ListItem("Dreggoog the Downunder"));
        step30.add(new ListItem("Dreggooniz the Adventurous"));
        step30.add(new ListItem("Dreggrieg the Pianist"));
        step30.add(new ListItem("Dreggooliz the Macho"));
        step30.add(new ListItem("Booty the Beast"));
        step30.add(new ListItem("Crokdylann the Rebel"));
        step30.add(new ListItem("Dragangora the Softy"));
        step30.add(new ListItem("Kaniedoss the Giggling"));
        step30.add(new ListItem("Kidodo the Extinct"));
        step30.add(new ListItem("Killua the Assassin"));
        step30.add(new ListItem("Chukoalak the Norris"));
        step30.add(new ListItem("Mufavabeenz the Cannibal"));
        step30.add(new ListItem("Pandali the Surreal"));
        step30.add(new ListItem("Prikoko the Witless"));
        step30.add(new ListItem("Crackedral the Majestic"));
        step30.add(new ListItem("Tanukhiraru the Gifted"));
        step30.add(new ListItem("Tanaked the Stalker"));
        step30.add(new ListItem("Tanno the Dominator"));
        step30.add(new ListItem("Tanukhuina the Drawer"));
        step30.add(new ListItem("TanuKiki the Deliveryghost"));
        listChild.put(listHeader.get(29), step30);

       List<ListItem> step31 = new ArrayList<>();
        step31.add(new ListItem("Warko the Inky"));
        step31.add(new ListItem("Bulbigroov the Dancer"));
        step31.add(new ListItem("Pikhoven the Deaf"));
        step31.add(new ListItem("Treekalack the Sad"));
        step31.add(new ListItem("Treekonk the Stunned"));
        step31.add(new ListItem("Fisheralf the Stewart"));
        step31.add(new ListItem("Dragory the Violent"));
        step31.add(new ListItem("Greetdoff the Gentleman"));
        step31.add(new ListItem("Bambono the Holy"));
        step31.add(new ListItem("Kitsouie the Green"));
        step31.add(new ListItem("Popoalak the Mousibrown"));
        step31.add(new ListItem("Koalarchitect the Balancing Force"));
        step31.add(new ListItem("Koelloggs the Creator"));
        step31.add(new ListItem("Moops the Bubbleboy"));
        step31.add(new ListItem("Oni'orses the Foolish"));
        step31.add(new ListItem("Piralhaka the Intimidator"));
        step31.add(new ListItem("Pocher the Kingponger"));
        step31.add(new ListItem("Floratio the Investigator"));
        step31.add(new ListItem("Dregguantico the Trainer"));
        step31.add(new ListItem("Ezothbeitor the Neighbour"));
        listChild.put(listHeader.get(30), step31);

        List<ListItem> step32 = new ArrayList<>();
        step32.add(new ListItem("Barkricrac the Unsteady"));
        step32.add(new ListItem("Draghouse the Cynical"));
        step32.add(new ListItem("Koaldman the Garish"));
        step32.add(new ListItem("Treekness the Dark"));
        step32.add(new ListItem("Drakoamax the Mad"));
        step32.add(new ListItem("Floramodovar the Stoned"));
        step32.add(new ListItem("Draigovsky the SocalledSwan"));
        step32.add(new ListItem("Kitsuey the Red"));
        step32.add(new ListItem("Jackoalak the Moonwalker"));
        step32.add(new ListItem("Ryukualak the Bored"));
        step32.add(new ListItem("Mamankalak the Bibliomaniac"));
        step32.add(new ListItem("Mopidyk the Mire"));
        step32.add(new ListItem("Mopfeet the Circular"));
        step32.add(new ListItem("Worka the Willful"));
        step32.add(new ListItem("Dragossiper the Nag"));
        step32.add(new ListItem("Sorgyo Quiretox the Chatterbox"));
        step32.add(new ListItem("Dragorse the Wild"));
        step32.add(new ListItem("Koalsen the Similar"));
        step32.add(new ListItem("Calipzoth the Icy"));
        step32.add(new ListItem("Zigzoth the Indecisive"));
        listChild.put(listHeader.get(31), step32);

        List<ListItem> step33 = new ArrayList<>();
        step33.add(new ListItem("Follikoko the Tufted"));
        step33.add(new ListItem("Cheech the Pussycat"));
        step33.add(new ListItem("Koalakropolis the King of the Hill"));
        step33.add(new ListItem("Leorio the Haunted"));
        step33.add(new ListItem("Treekstalbal the Psychic"));
        step33.add(new ListItem("Mushketeer the Loyal"));
        step33.add(new ListItem("Mushuliet the Catapulet"));
        step33.add(new ListItem("Romush the Montecchi"));
        step33.add(new ListItem("Edvushmunch the Screamer"));
        step33.add(new ListItem("Nidsally the Mushtang"));
        step33.add(new ListItem("Pandipoopik the Wondrous"));
        step33.add(new ListItem("Pandumonium the Joker"));
        step33.add(new ListItem("MoMaho the Modernist"));
        step33.add(new ListItem("Snailmetalika the Garagician"));
        step33.add(new ListItem("Satonuki the Plastikpaddy"));
        step33.add(new ListItem("Trooligan the Bulldogg"));
        step33.add(new ListItem("Trumpaynor the Survivor"));
        step33.add(new ListItem("Yokai the Choral"));
        step33.add(new ListItem("Don Quizothe the Stubborn"));
        step33.add(new ListItem("Zouzoth the Cuddly"));
        listChild.put(listHeader.get(32), step33);

        List<ListItem> step34 = new ArrayList<>();
        step34.add(new ListItem("Miomaho the Siciliano"));
        step34.add(new ListItem("Roy the Rover"));
        step34.add(new ListItem("Pandora the Explorer"));
        step34.add(new ListItem("Pandoracle the Opposing Force"));
        step34.add(new ListItem("Tanuktonik the Doofdoof"));
        step34.add(new ListItem("Yoksai the Spirited"));
        listChild.put(listHeader.get(33), step34);

        List<ListItem> step35 = new ArrayList<>();
        step35.add(new ListItem("Kralove Soul"));
        listChild.put(listHeader.get(34), step35);
    }

}
