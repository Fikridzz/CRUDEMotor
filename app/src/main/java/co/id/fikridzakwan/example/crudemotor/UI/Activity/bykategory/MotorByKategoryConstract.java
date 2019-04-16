package co.id.fikridzakwan.example.crudemotor.UI.Activity.bykategory;

import java.util.List;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface MotorByKategoryConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showListMotorByKategory(List<MotorData> mototByKategoryList);
        void showFailureMessage(String msg);
    }
    interface Presenter {
        void getListMotorByKategory(String idKategory);
    }
}
