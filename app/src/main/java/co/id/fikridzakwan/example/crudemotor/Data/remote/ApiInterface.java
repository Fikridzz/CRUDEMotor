package co.id.fikridzakwan.example.crudemotor.Data.remote;

import co.id.fikridzakwan.example.crudemotor.Model.detail.DetailMotorResponse;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginResponse;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import co.id.fikridzakwan.example.crudemotor.Model.upload.UploadResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginuser.php")
    Call<LoginResponse> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("registeruser.php")
    Call<LoginResponse> registerUser(@Field("namauser") String namauser,
                                     @Field("alamat") String alamat,
                                     @Field("notelp") String notelp,
                                     @Field("jenkel") String jenkel,
                                     @Field("username") String username,
                                     @Field("password") String password,
                                     @Field("level") String level);

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<LoginResponse> updateUser(@Field("iduser") int iduser,
                                   @Field("namauser") String namauser,
                                   @Field("alamat") String alamat,
                                   @Field("jenkel") String jenkel,
                                   @Field("notelp") String notelp);

    // Mengambil data news
    @GET("getmotorbaru.php")
    Call<MotorResponse> getMotorNews();

    // Mengambil data populer
    @GET("getmotorpopuler.php")
    Call<MotorResponse> getMotorPopuler();

    // Mengambil data kategory
    @GET("getkategori.php")
    Call<MotorResponse> getMotorKategory();

    // Mengupload motor
    @Multipart
    @POST("uploadmotor.php")
    Call<UploadResponse> uploadMotor(@Part("iduser") int idUser,
                                     @Part("idkategori") int idKategori,
                                     @Part("namamotor") RequestBody namaMotor,
                                     @Part("descmotor") RequestBody descMotor,
                                     @Part MultipartBody.Part image);

    // Mengambil detail motor
    @GET("getdetailmotor.php")
    Call<DetailMotorResponse> getDetailMotor(@Query("idmotor") int idMotor);

    // Mengambil data berdasarkan kategory
    @GET("getmotorbykategori.php")
    Call<MotorResponse> getMotorByKategory(@Query("idkategori") int idKategory);

    // Mengambil data bedasarkan user
    @GET("getmotorbyuser.php")
    Call<MotorResponse> getMotorByUser(@Query("iduser") int idUser);

    // Delete motor
    @FormUrlEncoded
    @POST("deletemotor.php")
    Call<MotorResponse> deleteMotor(@Field("idmotor") int idMotor,
                                    @Field("fotomotor") String fotoMotor);

    // Update motor
    @Multipart
    @POST("updatemotor.php")
    Call<MotorResponse> updateMotor(@Part("idmotor") int idMotor,
                                    @Part("idkategori") int idKategori,
                                    @Part("namamotor") RequestBody namaMotor,
                                    @Part("descmotor") RequestBody descMotor,
                                    @Part("fotomotor") RequestBody fotoMotor,
                                    @Part MultipartBody.Part image);
}
