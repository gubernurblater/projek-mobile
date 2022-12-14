package com.example.projekaplikasimobilefix;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class PembayaranActivity extends AppCompatActivity {
TextView etharga,etjadwal,etlapangan;
Button btnInsert;
int duration = Toast.LENGTH_SHORT;
String uRL ="http://192.168.1.6/php/orion/insertdata.php";
    private CardView cardView;
    private ImageView imageView;
    private Button button;
    private CharSequence[] options= {"Camera","Gallery","Cancel"};
    private FloatingActionButton fabMulti;

    private String selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        etharga = findViewById(R.id.harga);
        etjadwal = findViewById(R.id.jadwal);
        etlapangan = findViewById(R.id.lapangan);
        btnInsert = findViewById(R.id.btnInsert);

        //upload foto
        cardView = findViewById(R.id.cardview);
        imageView = findViewById(R.id.imageview);
        button = findViewById(R.id.button);


        requirePermission();

        String username = getIntent().getExtras().getString("username");
        String lapangan = getIntent().getExtras().getString("lapangan");
        String jadwal = getIntent().getExtras().getString("jadwal");


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PembayaranActivity.this);
                builder.setTitle("Select Image");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(options[which].equals("Camera")){
                            Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePic, 0);
                        }
                        else if(options[which].equals("Gallery")) {
                            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gallery, 1);
                        }
                        else {
                            dialog.dismiss();
                        }
                    }
                });

                builder.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFileToServer();
            }
        });



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, uRL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Toast.makeText(PembayaranActivity.this,"Data berhasil dikirim!!", duration).show();
                                btnInsert.setClickable(false);
                            } else if (response.equals("failure")) {
                                Toast.makeText(PembayaranActivity.this,"Something went wrong!!", duration).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PembayaranActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> param = new HashMap<>();
                            param.put("lapangan",lapangan);
                            param.put("jadwal",jadwal);
                            param.put("username",username);
                            return param;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(PembayaranActivity.this.getApplicationContext());
                    requestQueue.add(stringRequest);

            }
        });
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode !=RESULT_CANCELED){

            switch (requestCode){
                case 0:
                    if(resultCode == RESULT_OK && data !=null){
                        Bitmap image = (Bitmap) data.getExtras().get("data");
                        selectedImage = FileUtils.getPath(PembayaranActivity.this, getImageUri(PembayaranActivity.this,image));
                        imageView.setImageBitmap(image);
                    }
                    break;
                case 1:
                    if(resultCode == RESULT_OK && data !=null){

                        Uri image = data.getData();
                        selectedImage = FileUtils.getPath(PembayaranActivity.this,image);
                        Picasso.get().load(image).into(imageView);
                    }
            }

        }
    }

    public Uri getImageUri(Context context, Bitmap bitmap){
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "myImage","");

        return Uri.parse(path);
    }


    public void requirePermission(){
        ActivityCompat.requestPermissions(PembayaranActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }


    public void uploadFileToServer(){

        File file = new File(Uri.parse(selectedImage).getPath());

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("sendimage",file.getName(),requestBody);

        HttpService service = RetrofitBuilder.getClient().create(HttpService.class);

        Call<FileModel> call = service.callUploadApi(filePart);
        call.enqueue(new Callback<FileModel>() {
            @Override
            public void onResponse(Call<FileModel> call, retrofit2.Response<FileModel> response) {
                FileModel fileModel = response.body();
                Toast.makeText(PembayaranActivity.this, fileModel.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<FileModel> call, Throwable t) {
                Toast.makeText(PembayaranActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }




        }
