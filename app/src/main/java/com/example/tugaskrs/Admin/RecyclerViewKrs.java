package com.example.tugaskrs.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tugaskrs.Admin.Adapter.KrsAdapter;
import com.example.tugaskrs.Admin.Model.Krs;
import com.example.tugaskrs.R;

import java.util.ArrayList;

public class RecyclerViewKrs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KrsAdapter krsAdapter;
    private ArrayList<Krs> krsArrayList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewKrs.this, CreateKrsActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_krs);
        this.setTitle("SI KRS - Hai Admin");

        tambahData();

        recyclerView = findViewById(R.id.rvKrs);
        krsAdapter = new KrsAdapter(krsArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewKrs.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(krsAdapter);

    }

    public void tambahData(){
        krsArrayList = new ArrayList<>();
        krsArrayList.add(new Krs("SIA","SISTEM BASIS DATA","Senin","3","1","YETLI","60"));
        krsArrayList.add(new Krs("SIA","SISTEM BASIS DATA","Senin","3","1","YETLI","60"));
        krsArrayList.add(new Krs("SIA","SISTEM BASIS DATA","Senin","3","1","YETLI","60"));
        krsArrayList.add(new Krs("SIA","SISTEM BASIS DATA","Senin","3","1","YETLI","60"));
    }
}
