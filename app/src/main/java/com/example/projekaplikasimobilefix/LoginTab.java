package com.example.projekaplikasimobilefix;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class LoginTab extends Fragment {
    private EditText etEmaillogin, etPasswordlogin;
    private String emaillogin, passwordlogin;
    int duration = Toast.LENGTH_SHORT;
    Button btnLogin;
    private String URL = "http://192.168.1.6/php/orion/login.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_login_tab, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEmaillogin = view.findViewById(R.id.emaillogin);
        etPasswordlogin = view.findViewById(R.id.passlogin);
        btnLogin = view.findViewById(R.id.buttonlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emaillogin = etEmaillogin.getText().toString().trim();
                passwordlogin = etPasswordlogin.getText().toString().trim();
                if(!emaillogin.equals("")&&!passwordlogin.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("username",emaillogin);
                                startActivity(intent);
                            } else if (response.equals("failure")) {
                                Toast.makeText(getActivity(),"Invalid Login Email/Password", duration).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.toString().trim(),Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("email",emaillogin);
                            data.put("password",passwordlogin);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    requestQueue.add(stringRequest);

                }else{
                    Toast.makeText(getActivity(),"Field harus di isi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
