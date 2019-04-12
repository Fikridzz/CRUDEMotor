package co.id.fikridzakwan.example.crudemotor.Model.motor;

import com.google.gson.annotations.SerializedName;

public class MotorData {

    @SerializedName("id_motor")
    private String idMotor;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("id_kategori")
    private String idKategori;

    @SerializedName("nama_motor")
    private String namaMotor;

    @SerializedName("desc_motor")
    private String descMotor;

    @SerializedName("foto_motor")
    private String fotoMotor ;

    @SerializedName("insert_time")
    private String insertTime;

    @SerializedName("view")
    private String view;

    @SerializedName("nama_user")
    private String namaUser;

    @SerializedName("nama_kategori")
    private String namaKategori;

    @SerializedName("url_motor")
    private String urlMotor;

    public String getIdMotor() {
        return idMotor;
    }

    public void setIdMotor(String idMotor) {
        this.idMotor = idMotor;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaMotor() {
        return namaMotor;
    }

    public void setNamaMotor(String namaMotor) {
        this.namaMotor = namaMotor;
    }

    public String getDescMotor() {
        return descMotor;
    }

    public void setDescMotor(String descMotor) {
        this.descMotor = descMotor;
    }

    public String getFotoMotor() {
        return fotoMotor;
    }

    public void setFotoMotor(String fotoMotor) {
        this.fotoMotor = fotoMotor;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getUrlMotor() {
        return urlMotor;
    }

    public void setUrlMotor(String urlMotor) {
        this.urlMotor = urlMotor;
    }
}
