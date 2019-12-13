package com.example.tugaskrs.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaskrs.Admin.Adapter.MatkulAdapter;
import com.example.tugaskrs.Admin.Model.Matakuliah;
import com.example.tugaskrs.R;

import java.util.ArrayList;

public class RecyclerViewDaftarMatkul extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MatkulAdapter matkulAdapter;
    private ArrayList<Matakuliah> mkList;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDaftarMatkul.this,CreateMatkulActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_matkul);
        this.setTitle("SI KRS - Hai Admin");

        tambahData();

        recyclerView = findViewById(R.id.rvMatkul);
        matkulAdapter = new MatkulAdapter(mkList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarMatkul.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(matkulAdapter);
    }

    public void tambahData(){
        mkList = new ArrayList<>();
        mkList.add(new Matakuliah("SI1A","SISTEM BASIS DATA","SENIN","3","3"));
        mkList.add(new Matakuliah("SI1B","MATRIKULASI","RABU","0","4"));
        mkList.add(new Matakuliah("SI2C","ALPRO STRUKDAT","KAMIS","3","1"));
    }
}
