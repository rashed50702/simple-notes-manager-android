package com.notes.iit.simplenotesmanager;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Note>{

    private ArrayList<Note> dataSet;
    Context mContext;
    private int lastPosition = -1;
    private static class ViewHolder {
        TextView title;
        TextView notePreview;
        TextView date;
    }

    public CustomAdapter(ArrayList<Note> data, Context context) {
        super(context, R.layout.list_row, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            viewHolder.title =  convertView.findViewById(R.id.noteTitle);
            viewHolder.notePreview = convertView.findViewById(R.id.notePreview);
            viewHolder.date = convertView.findViewById(R.id.datetime);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        String title=note.description.split("\n")[0];
        String preview="";
        if(note.description.contains("\n")) {
            preview = note.description.split("\n")[1];
        }
        viewHolder.title.setText(title);
        viewHolder.notePreview.setText(preview);
        viewHolder.date.setText(note.date);
        return convertView;
    }
}

