package co.id.fikridzakwan.example.crudemotor.Model.login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginData implements Parcelable {

    @SerializedName("id_user")
    private String iduser;

    @SerializedName("nama_user")
    private String namauser;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("jenkel")
    private String jenkel;

    @SerializedName("no_telp")
    private String notelp;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("level")
    private String level;

    public LoginData() {

    }

    protected LoginData(Parcel in) {
        iduser = in.readString();
        namauser = in.readString();
        alamat = in.readString();
        jenkel = in.readString();
        notelp = in.readString();
        username = in.readString();
        password = in.readString();
        level = in.readString();
    }

    public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
        @Override
        public LoginData createFromParcel(Parcel in) {
            return new LoginData(in);
        }

        @Override
        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNamauser() {
        return namauser;
    }

    public void setNamauser(String namauser) {
        this.namauser = namauser;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iduser);
        dest.writeString(namauser);
        dest.writeString(alamat);
        dest.writeString(jenkel);
        dest.writeString(notelp);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(level);
    }
}
