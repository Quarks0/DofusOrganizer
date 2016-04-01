package com.quarks0.dofusorganizer;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<String>> listChild;

    public ExpandableListAdapter(Context _context, List<String> _listHeader, HashMap<String,List<String>> _listChild){
        this.context = _context;
        this.listHeader = _listHeader;
        this.listChild = _listChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        return this.listChild.get(this.listHeader.get(groupPosition)).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup parent){
        final String childText = (String) getChild(groupPosition, childPosition);

        if (view == null){
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.listitem,null);
        }
        TextView txtListChild = (TextView) view.findViewById(R.id.expListChild);
        txtListChild.setText(childText);
        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return this.listChild.get(this.listHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return this.listHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount(){
        return this.listHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition,boolean isExpanded,View view, ViewGroup viewGroup){
        String headerTitle = (String) getGroup(groupPosition);

        if(view == null){
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.listgroup,null);
        }

        TextView labelListHeader = (TextView) view.findViewById(R.id.expListHeader);
        labelListHeader.setTypeface(null, Typeface.BOLD);
        labelListHeader.setText(headerTitle);

        return view;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }
}
