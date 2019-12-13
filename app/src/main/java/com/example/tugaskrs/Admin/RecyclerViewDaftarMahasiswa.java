package com.example.tugaskrs.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugaskrs.Admin.Adapter.MahasiswaAdapter;
import com.example.tugaskrs.Admin.Model.Mahasiswa;
import com.example.tugaskrs.DataMahasiswaService;
import com.example.tugaskrs.R;
import com.example.tugaskrs.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewDaftarMahasiswa extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private ArrayList<Mahasiswa> mahasiswalist;
    ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucreate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu1){
            Intent intent = new Intent(RecyclerViewDaftarMahasiswa.this,CreateMahasiswaActivity.class);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_daftar_dosen);
        this.setTitle("SI KRS - Hai Admin");
        //tambahData();
        //addData
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        DataMahasiswaService service = RetrofitClient.getRetrofitInstance().create(DataMahasiswaService.class);
        Call<ArrayList<Mahasiswa>> call = service.getMahasiswaAll("72170110");
        call.enqueue(new Callback<ArrayList<Mahasiswa>>() {
            @Override
            public void onResponse(Call<ArrayList<Mahasiswa>> call, Response<ArrayList<Mahasiswa>> response) {
                progressDialog.dismiss();

                recyclerView = findViewById(R.id.rvMhs);
                mahasiswaAdapter = new MahasiswaAdapter(response.body());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewDaftarMahasiswa.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(mahasiswaAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Mahasiswa>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RecyclerViewDaftarMahasiswa.this, "Login Gagal, Silahkan Coba Lagi", Toast.LENGTH_SHORT);
            }
        });
    }
}
