package co.id.fikridzakwan.example.crudemotor.UI.Activity.upload;

import android.content.Context;
import android.net.Uri;

import java.util.List;

import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;

public interface UploadConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showMessage(String msg);
        void successUpload();
        void showSpinnerCategory(List<MotorData> motorKategoryDataList);
    }

    interface Presenter {
        void getKategory();
        void uploadMotor(Context context,
                         Uri filePath,
                         String namaMotor,
                         String descMotor,
                         String idKategory);
    }
}
