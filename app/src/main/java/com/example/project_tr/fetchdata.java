package com.example.project_tr;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;

import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fetchdata extends AppCompatActivity
{
    RecyclerView recyclerView;
    ArrayList<model> dataholder;

    //
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor=new dbmanager(this).readalldata();
        dataholder=new ArrayList<>();

        while(cursor.moveToNext())
        {
            model obj=new model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            dataholder.add(obj);
        }

        myadapter adapter=new myadapter(dataholder);
        recyclerView.setAdapter(adapter);

        ////////////////////////////////////////////////////////
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text)
    {

        ArrayList<model> filteredList = new ArrayList<>();
        for (model item : dataholder )
        {
            if (item.getDestination().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }

        if(filteredList.isEmpty())
        {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            myadapter.pb(filteredList);
        }

    }


}


