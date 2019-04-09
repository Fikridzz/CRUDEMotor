package co.id.fikridzakwan.example.crudemotor.Data.remote;

import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
