package co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile;

import android.content.Context;

import java.util.List;

import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface ProfileConstract {
    interface View {
        void showMotorListByUser(List<MotorData> motorDataList);
        void showFailurMessage(String msg);
        void showDataUser(LoginData loginData);
    }
    interface Presenter {
        void getDataUser(Context context);
        void getMotorListByUser(String idUser);
        void logoutSesion(Context context);
    }
}
