package co.id.fikridzakwan.example.crudemotor.UI.Activity.editbyuser;

import android.content.Context;
import android.net.Uri;

import java.util.List;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface EditByUserConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDetailMotor(MotorData motorData);
        void showMessage(String msg);
        void successUpdate();
        void showSpinnerKategory(List<MotorData> kategoryDataList);
    }
    interface Presenter {
        void getKategory();
        void getDetailMotor(String idMotor);
        void updateDataMotor(Context context, String namaMotor, String descMotor, String idKategory, String idMotor);
    }
}
