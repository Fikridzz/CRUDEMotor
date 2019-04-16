package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailbyuser;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface DetailByUserConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void successDelete();
        void successUpdate();
        void showDetailMotor(MotorData motorData);
        void showFailurMessage(String msg);
    }

    interface Presenter {
        void getDetailMotor();
        void updateDetailMotor(Context context, String namaMotor, String descMotor, String idKategory, String idMotor);
        void deleteDetailMotor(String idMotor, String namaFotoMotor);
    }
}
