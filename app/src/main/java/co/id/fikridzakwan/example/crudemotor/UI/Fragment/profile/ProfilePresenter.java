package co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;

public class ProfilePresenter {

    private final ProfileConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public ProfilePresenter(ProfileConstract.View view) {
        this.view = view;
    }

}
