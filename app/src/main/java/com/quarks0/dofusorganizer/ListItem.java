package com.quarks0.dofusorganizer;

/**
 * Created by Quarks0 on 4/8/2016.
 */
public class ListItem{
    public String value;
    public boolean isToggled = false;
    public int counter = 0;

    public ListItem (String _value){
        value = _value;
    }

    public void addCounter(){
        counter++;
    }

    public boolean isChecked(){
        return isToggled;
    }

    public boolean itemToggle(){
        isToggled = !isToggled;
        return isToggled;
    }

    public int counterValue(){
        return counter;
    }

    public void subCounter(){
        counter--;
    }

    @Override
    public String toString(){
        return value;
    }


}
