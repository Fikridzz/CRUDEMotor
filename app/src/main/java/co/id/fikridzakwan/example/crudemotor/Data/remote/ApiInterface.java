package co.id.fikridzakwan.example.crudemotor.Data.remote;

import co.id.fikridzakwan.example.crudemotor.Model.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
