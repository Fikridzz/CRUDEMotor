package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailbyuser;

import android.content.Context;
import android.widget.PopupMenu;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface DetailByUserConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void successDelete();
        void showDetailMotor(MotorData motorData);
        void showFailurMessage(String msg);
        void showMessage(String msg);
    }

    interface Presenter {
        void getDetailMotor(String idMotor);
        void deleteDetailMotor(String idMotor, String namaFotoMotor);
    }
}
