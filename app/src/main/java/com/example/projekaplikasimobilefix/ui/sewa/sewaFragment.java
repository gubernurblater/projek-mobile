package com.example.projekaplikasimobilefix.ui.sewa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projekaplikasimobilefix.MainActivity;
import com.example.projekaplikasimobilefix.PemesananActivity;
import com.example.projekaplikasimobilefix.R;


import com.example.projekaplikasimobilefix.databinding.ActivityPemesananBinding;

public class sewaFragment extends Fragment {
Button btn1,btn2,btn3,btn4;
    String dataUsername ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mengambil data username dari main activity
       MainActivity activity = (MainActivity) getActivity();
       dataUsername = activity.getUsername();
        return inflater.inflate(R.layout.fragment_sewa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn1 = view.findViewById(R.id.btnPesanVinyl);
        btn2 = view.findViewById(R.id.btnPesanSintetis);
        btn3 = view.findViewById(R.id.btnPesanVinyl2);
        btn4 = view.findViewById(R.id.btnPesanSintetis2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PemesananActivity.class);
                String harga = "100000";
                String lapangan = "Lapangan Futsal 3";
                intent.putExtra("harga",harga);
                intent.putExtra("lapangan",lapangan);
                intent.putExtra("username",dataUsername);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PemesananActivity.class);
                String harga = "120000";
                String lapangan = "Lapangan Futsal 1";
                intent.putExtra("harga",harga);
                intent.putExtra("lapangan",lapangan);
                intent.putExtra("username",dataUsername);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PemesananActivity.class);
                String harga = "100000";
                String lapangan = "Lapangan Futsal 3";
                intent.putExtra("harga",harga);
                intent.putExtra("lapangan",lapangan);
                intent.putExtra("username",dataUsername);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PemesananActivity.class);
                String harga = "120000";
                String lapangan = "Lapangan Futsal 2";
                intent.putExtra("harga",harga);
                intent.putExtra("lapangan",lapangan);
                intent.putExtra("username",dataUsername);
                startActivity(intent);
            }
        });

    }
}