package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailbyuser;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.detailmotor.DetailMotorConstract;

public class DetailByUserPresenter implements DetailByUserConstract.Presenter {

    private final DetailByUserConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public DetailByUserPresenter(DetailByUserConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailMotor() {

    }

    @Override
    public void updateDetailMotor(Context context, String namaMotor, String descMotor, String idKategory, String idMotor) {

    }

    @Override
    public void deleteDetailMotor(String idMotor, String namaFotoMotor) {

    }
}
