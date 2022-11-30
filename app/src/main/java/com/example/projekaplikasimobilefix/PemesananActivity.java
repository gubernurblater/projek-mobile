package com.example.projekaplikasimobilefix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class PemesananActivity extends AppCompatActivity {

    private CheckBox satu, dua, tiga, empat, lima, enam, tujuh, delapan, sembilan, sepuluh, sebelas, duabelas;
    private Button conJam,conOrder;
    private TextView tampil,tvharga,tvlapangan;
    String jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        satu = findViewById(R.id.satu);
        dua = findViewById(R.id.dua);
        tiga = findViewById(R.id.tiga);
        empat = findViewById(R.id.empat);
        lima = findViewById(R.id.lima);
        enam = findViewById(R.id.enam);
        tujuh = findViewById(R.id.tujuh);
        delapan = findViewById(R.id.delapan);
        sembilan = findViewById(R.id.sembilan);
        sepuluh = findViewById(R.id.sepuluh);
        sebelas = findViewById(R.id.sebelas);
        duabelas = findViewById(R.id.duabelas);
        conJam = findViewById(R.id.btnConJam);
        conOrder = findViewById(R.id.btnConPesan);
        //text view detail pesanan
        tampil = findViewById(R.id.tampil);
        tvharga = findViewById(R.id.harga);
        tvlapangan = findViewById(R.id.lapangan);

        //data dari fragment sewa
        String username = getIntent().getExtras().getString("username");
        String lapangan = getIntent().getExtras().getString("lapangan");


        conJam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!satu.isChecked() && !dua.isChecked() && !tiga.isChecked() && !empat.isChecked() && !lima.isChecked() && !enam.isChecked() && !tujuh.isChecked() && !delapan.isChecked() && !sembilan.isChecked() && !sepuluh.isChecked() && !sebelas.isChecked() && !duabelas.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Tidak ada jam yang dipilih", Toast.LENGTH_SHORT).show();
                }
                else {
                    String a = "";
                    if (satu.isChecked()){
                        a += "08:00 - 09:00\n";
                    }
                    String b = "";
                    if (dua.isChecked()){
                        b += "09:00 - 10:00\n";
                    }
                    String c = "";
                    if (tiga.isChecked()){
                        c += "10:00 - 11:00\n";
                    }
                    String d = "";
                    if (empat.isChecked()){
                        d += "11:00 - 12:00\n";
                    }
                    String e = "";
                    if (lima.isChecked()){
                        e += "12:00 - 13:00\n";
                    }
                    String f = "";
                    if (enam.isChecked()){
                        f += "13:00 - 14:00\n";
                    }
                    String g = "";
                    if (tujuh.isChecked()){
                        g += "14:00 - 15:00\n";
                    }
                    String h = "";
                    if (delapan.isChecked()){
                        h += "15:00 - 16:00\n";
                    }
                    String i = "";
                    if (sembilan.isChecked()){
                        i += "16:00 - 17:00\n";
                    }
                    String j = "";
                    if (sepuluh.isChecked()){
                        j += "17:00 - 18:00\n";
                    }
                    String k = "";
                    if (sebelas.isChecked()){
                        k += "18:00 - 19:00\n";
                    }
                    String l = "";
                    if (duabelas.isChecked()){
                        l += "19:00 - 20:00\n";
                    }
                    tampil.setText( a + "" + b + "" + c + "" + d + "" + e + "" + f + "" + g + "" + h + "" + i + "" + j + "" + k + "" + l);
                    jadwal = tampil.getText().toString();
                }
            }
        });

        conOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PemesananActivity.this, PembayaranActivity.class);
                pindah.putExtra("username",username);
                pindah.putExtra("lapangan",lapangan);
                pindah.putExtra("jadwal",jadwal);
                startActivity(pindah);
            }
        });
    }
}