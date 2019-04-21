package co.id.fikridzakwan.example.crudemotor.UI.Fragment.home;

import java.util.List;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface HomeConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showMotorListKategory(List<MotorData> motorKategoryList);
        void showMotorListPopuler(List<MotorData> motorPopulerList);
        void showMotorListNews(List<MotorData> motorNewsList);
        void showFailurMessage(String msg);
    }
    interface Presenter {
        void getListKategoryMotor();
        void getListPopulerMotor();
        void getListNewsmotor();
    }
}
