package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailmotor;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface DetailMotorConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDetailMotor(MotorData motorData);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDetailMotor(String idMotor);
    }
}
