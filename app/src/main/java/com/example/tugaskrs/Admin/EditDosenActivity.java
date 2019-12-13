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
import com.example.tugaskrs.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.tugaskrs.DataDosenService;
import com.example.tugaskrs.RetrofitClient;


public class EditDosenActivity extends AppCompatActivity {

    EditText edtId, edtNama, edtNidn, edtAlamat, edtEmail, edtGelar;
    Intent mIntent = getIntent();
    DataDosenService service;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dosen);
        this.setTitle("SI KRS - Hai Admin");

        Button btnEdit = (Button)findViewById(R.id.btnEditDosen);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditDosenActivity.this);
                if (edtNama.length() == 0) {
                    //jika form Email belum di isi / masih kosong
                    edtNama.setError("Silahkan mengisi nama dosen");
                } else if (edtNidn.getText().toString().length() == 0) {
                    //jika form Username belum di isi / masih kosong
                    edtNidn.setError("Silahkan mengisi NIDN");
                } else if (edtGelar.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    edtGelar.setError("Silahkan mengisi gelar");
                } else if (edtAlamat.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    edtAlamat.setError("Silahkan mengisi alamat");
                } else if (edtEmail.getText().toString().length() == 0) {
                    //jika form Passwrod belum di isi / masih kosong
                    edtEmail.setError("Silahkan mengisi nama email");
                } else {
                    builder.setMessage("Apakah anda yakin untuk menyimpan?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(EditDosenActivity.this, "Batal Update", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    requestUpdateDosen();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

    }

    private void requestUpdateDosen(){
        edtId = (EditText)findViewById(R.id.edtIdDosen);
        edtNama = (EditText)findViewById(R.id.edtNamaDsn);
        edtNidn = (EditText)findViewById(R.id.edtNidn);
        edtAlamat = (EditText)findViewById(R.id.edtAlamatDsn);
        edtEmail = (EditText)findViewById(R.id.edtEmailDsn);
        edtGelar = (EditText)findViewById(R.id.edtGelar);

        edtId.setText(mIntent.getStringExtra("id"));
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtNama.setText(mIntent.getStringExtra("nama"));
        edtNidn.setText(mIntent.getStringExtra("nidn"));
        edtAlamat.setText(mIntent.getStringExtra("alamat"));
        edtEmail.setText(mIntent.getStringExtra("email"));
        edtGelar.setText(mIntent.getStringExtra("gelar"));

        service = RetrofitClient.getRetrofitInstance().create(DataDosenService.class);
        progressDialog =  ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        Call<Dosen> call =  service.update_dosen(edtId.getText().toString(),edtNama.getText().toString(),edtNidn.getText().toString(),
                edtAlamat.getText().toString(),edtEmail.getText().toString(),edtGelar.getText().toString(),"https://picsum.photos/200",
                "72170110");
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                progressDialog.dismiss();
                Toast.makeText(EditDosenActivity.this,"Berhasil Update",Toast.LENGTH_LONG).show();
                Intent refresh = new Intent(EditDosenActivity.this, RecyclerViewDaftarDosen.class);
                startActivity(refresh);
                finish();

            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditDosenActivity.this,"Error",Toast.LENGTH_SHORT);
            }
        });
    }
}