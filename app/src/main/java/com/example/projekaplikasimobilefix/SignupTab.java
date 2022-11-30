package com.example.projekaplikasimobilefix;

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


public class SignupTab extends Fragment {
    private EditText etUsername, etEmailreg, etPasswordreg, etPasswordreg2;
    private String username, emailreg, passwordreg,passwordreg2;
    Button btnReg;
    private String uRL = "http://192.168.1.6/php/orion/register.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_tab, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int duration = Toast.LENGTH_SHORT;
        etUsername = view.findViewById(R.id.username);
        etEmailreg = view.findViewById(R.id.emailreg);
        etPasswordreg = view.findViewById(R.id.passwordreg);
        etPasswordreg2 = view.findViewById(R.id.passwordreg2);
        btnReg= view.findViewById(R.id.button);
        username = emailreg = passwordreg = passwordreg2 = "";

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().trim();
                emailreg = etEmailreg.getText().toString().trim();
                passwordreg = etPasswordreg.getText().toString().trim();
                passwordreg2 = etPasswordreg2.getText().toString().trim();

                if(!passwordreg.equals(passwordreg2)){
                    Toast.makeText(getActivity(),"Password tidak sesuai", duration).show();
                }else if(!username.equals("")&&!emailreg.equals("")&&!passwordreg.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, uRL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Toast.makeText(getActivity(),"Registrasi Berhasil!!", duration).show();
                                btnReg.setClickable(false);
                            } else if (response.equals("failure")) {
                                Toast.makeText(getActivity(),"Something went wrong!!", duration).show();
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
                            Map<String, String> param = new HashMap<>();
                            param.put("username",username);
                            param.put("email",emailreg);
                            param.put("password",passwordreg);
                            return param;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });


    }
}