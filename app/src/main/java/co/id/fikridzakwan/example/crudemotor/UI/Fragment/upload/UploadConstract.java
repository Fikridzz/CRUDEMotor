package co.id.fikridzakwan.example.crudemotor.UI.Fragment.upload;

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
        void showSpinnerKategory(List<MotorData> kategoryDataList);
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
