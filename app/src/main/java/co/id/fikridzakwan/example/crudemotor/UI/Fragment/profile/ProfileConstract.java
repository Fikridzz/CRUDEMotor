package co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile;

import java.util.List;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface ProfileConstract {
    interface View {
        void showMotorListByUser(List<MotorData> motorDataList);
    }
}
