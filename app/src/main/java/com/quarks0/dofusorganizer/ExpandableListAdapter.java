package com.quarks0.dofusorganizer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Largely taken from somewhere else but I forget source
 */
//Implementation of a Nested List
public class ExpandableListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<ListItem>> listChild;

    public ExpandableListAdapter(Context _context, List<String> _listHeader, HashMap<String,List<ListItem>> _listChild){
        this.context = _context;
        this.listHeader = _listHeader;
        this.listChild = _listChild;
    }

    @Override
    public ListItem getChild(int groupPosition, int childPosition){
        return this.listChild.get(this.listHeader.get(groupPosition)).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        final ViewHolder viewHolderChild;
        final String childText = getChild(groupPosition,childPosition).value;
        final boolean toggleStatus = getChild(groupPosition,childPosition).isChecked();

        if (convertView == null){
            LayoutInflater layInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layInflater.inflate(R.layout.listitem,parent,false);

            viewHolderChild = new ViewHolder();
            viewHolderChild.position = childPosition;
            convertView.setTag(viewHolderChild);
        }
        else{
            viewHolderChild = (ViewHolder) convertView.getTag();
        }
        viewHolderChild.text=(TextView) convertView.findViewById(R.id.expListChild);
        viewHolderChild.text.setText(childText);
        viewHolderChild.toggle = toggleStatus;

        if(toggleStatus){
            convertView.setBackgroundColor(Color.parseColor("#41A317"));
        }
        else {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        return convertView;
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
        final ViewHolder viewHolderParent;

        if(view == null){
            LayoutInflater layInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layInflater.inflate(R.layout.listgroup,null);

            viewHolderParent = new ViewHolder();
            viewHolderParent.position = groupPosition;
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
