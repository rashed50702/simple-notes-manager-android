package com.notes.iit.simplenotesmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class NotesListActivity extends AppCompatActivity {
    FloatingActionButton noteEditOpenButton;
    ListView listView;
    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        initalizeViews();
        noteEditOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotesListActivity.this, ListEditActivity.class);
                startActivity(intent);
            }
        });
        sqliteHelper=new SqliteHelper(this);
        final Cursor cursor= sqliteHelper.retriveAllNotesCursor();
        final CursorAdapter cursorAdapter=new NotesListAdapter(this,cursor);
        listView.setAdapter(cursorAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                deleteNote();
                return true;
            }
        });

        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ListEditActivity.class);
                intent.putExtra("isUpdate", true);
                intent.putExtra("id", cursorAdapter.getItemId(i));//.get(position).get(KEY_ID))
                startActivity(intent);
            }
        });
    }

    private void initalizeViews() {
        noteEditOpenButton=(FloatingActionButton)findViewById(R.id.fab);
        listView=(ListView)findViewById(R.id.list);
    }

    private void deleteNote(){
        Toast.makeText(getApplicationContext(), "Long Clicked.", Toast.LENGTH_SHORT).show();
        AlertDialog confirmation = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure to delete this?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Deleted."+i, Toast.LENGTH_SHORT).show();
                        sqliteHelper.finalDeleteNote(i);
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

}
