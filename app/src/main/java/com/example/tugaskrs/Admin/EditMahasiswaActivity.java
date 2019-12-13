package com.example.tugaskrs.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugaskrs.Admin.Model.Dosen;
import com.example.tugaskrs.Admin.Model.Mahasiswa;
import com.example.tugaskrs.DataDosenService;
import com.example.tugaskrs.DataMahasiswaService;
import com.example.tugaskrs.R;
import com.example.tugaskrs.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMahasiswaActivity extends AppCompatActivity {

    EditText edtId, edtNama, edtNidn, edtAlamat, edtEmail;
    Intent mIntent = getIntent();
    DataMahasiswaService service;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mahasiswa);
        this.setTitle("SI KRS - Hai Admin");

        Button btnSimpan = (Button)findViewById(R.id.btnCreateMhs);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditMahasiswaActivity.this);
                if (edtNama.getText().toString().length() == 0) {
                    //jika form Email belum di isi / masih kosong
                    edtNama.setError("Silahkan mengisi nama Mahasiswa");
                } else if (edtNidn.getText().toString().length() == 0) {
                    //jika form Username belum di isi / masih kosong
                    edtNidn.setError("Silahkan mengisi NIM");
                } else if (edtAlamat.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    edtAlamat.setError("Silahkan mengisi alamat");
                } else if (edtEmail.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    edtEmail.setError("Silahkan mengisi nama email");
                }else {
                    builder.setMessage("Apakah anda yakin untuk menyimpan?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(EditMahasiswaActivity.this, "Batal Simpan", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    requestUpdateMahasiswa();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void requestUpdateMahasiswa(){
        edtId = (EditText)findViewById(R.id.edtIdMhs);
        edtNama = (EditText)findViewById(R.id.edtNamaMhs);
        edtNidn = (EditText)findViewById(R.id.edtNim);
        edtAlamat = (EditText)findViewById(R.id.edtAlamatMhs);
        edtEmail = (EditText)findViewById(R.id.edtEmailMhs);

        edtId.setText(mIntent.getStringExtra("id"));
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtNama.setText(mIntent.getStringExtra("nama"));
        edtNidn.setText(mIntent.getStringExtra("nidn"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtEmail.setText(mIntent.getStringExtra("email"));

        service = RetrofitClient.getRetrofitInstance().create(DataMahasiswaService.class);
        progressDialog =  ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<Mahasiswa> call =  service.update_mahasiswa(edtId.getText().toString(),edtNama.getText().toString(),edtNidn.getText().toString(),
                edtAlamat.getText().toString(),edtEmail.getText().toString(),"https://picsum.photos/200",
                "72170110");
        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                progressDialog.dismiss();
                Toast.makeText(EditMahasiswaActivity.this,"Berhasil Update",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(EditMahasiswaActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditMahasiswaActivity.this,"Error",Toast.LENGTH_SHORT);
            }
        });
    }
}