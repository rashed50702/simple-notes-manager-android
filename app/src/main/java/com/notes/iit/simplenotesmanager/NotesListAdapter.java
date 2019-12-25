package com.notes.iit.simplenotesmanager;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesListAdapter extends CursorAdapter {
    public NotesListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.noteTitle);
        TextView preview = (TextView) view.findViewById(R.id.notePreview);
        TextView date = (TextView) view.findViewById(R.id.datetime);
        String description = cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.KEY_DESCRIPTION));
//        String noteid = "1";
        String noteid = cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.NOTE_ID));
        String datetime=cursor.getString(cursor.getColumnIndexOrThrow(SqliteHelper.KEY_MODIFIEDDATE));
        String noteTitle=description.split("\n")[0];
        String notePreview="";
        if(description.contains("\n")) {
            notePreview = noteid + description.split("\n")[1];
        }
        title.setText(noteTitle + noteid);
        preview.setText(notePreview);
        date.setText(datetime);
    }
}
