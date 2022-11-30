package com.example.projekaplikasimobilefix.ui.transaksi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.widget.Button;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.projekaplikasimobilefix.DbContract;
import com.example.projekaplikasimobilefix.R;
import com.example.projekaplikasimobilefix.RecyclerAdapter;
import com.example.projekaplikasimobilefix.ui.Data;

public class transaksiFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    ArrayList<Data> arrayList = new ArrayList<>();
    String DATA_JSON_STRING, data_json_string;
    ProgressDialog progressDialog;
    int countData = 0;

    public transaksiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list_data);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog = new ProgressDialog(getActivity());
        // Read Data
        getJSON();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                readDataFromServer();
            }
        }, 1000);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false);

    }

    public void readDataFromServer() {
        if (checkNetworkConnection()) {
            arrayList.clear();
            try {
                JSONObject object = new JSONObject(data_json_string);
                JSONArray serverResponse = object.getJSONArray("server_response");
                String id, username,jadwal,status;

                while (countData < serverResponse.length()) {
                    JSONObject jsonObject = serverResponse.getJSONObject(countData);
                    id = jsonObject.getString("id_pesanan");
                    username = jsonObject.getString("username");
                    jadwal = jsonObject.getString("jadwal");
                    status = jsonObject.getString("status");

                    arrayList.add(new Data(id, username, jadwal, status));
                    countData++;
                }
                countData = 0;

                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void getJSON() {
        new BackgroundTask().execute();
    }



    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = DbContract.SERVER_GET_URL;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((DATA_JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(DATA_JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            data_json_string = result;
        }
    }
}
