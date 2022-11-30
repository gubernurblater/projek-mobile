package com.example.projekaplikasimobilefix;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HttpService {

    @Multipart
    @POST("php/orion/upload/upload_api.php")
    Call<FileModel> callUploadApi(@Part MultipartBody.Part image);

    @Multipart
    @POST("php/orion/upload/multi_upload.php")
    Call<FileModel> callMultipleUploadApi(@Part List<MultipartBody.Part> image);
}
