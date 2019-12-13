package com.example.tugaskrs.Dosen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tugaskrs.R;

public class DataDiri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri);

        Button btnDaftarKrs = (Button)findViewById(R.id.btnSimpanDataMhs);
        btnDaftarKrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataDiri.this, HomeDosen.class);
                startActivity(intent);
            }
        });
    }
}
