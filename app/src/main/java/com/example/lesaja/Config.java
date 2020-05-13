package com.example.lesaja;

public class Config {
//    public static final String URL_ADD="http://10.151.253.201/lesaja/addMurid.php";
//    public static final String URL_GET_MURID = "http://10.151.253.201/lesaja/lihatSatuMurid.php?notelp=";
    public static final String URL_ADD="http://192.168.0.5/lesaja/addMurid.php";
    public static final String URL_GET_MURID = "http://192.168.0.5/lesaja/lihatSatuMurid.php?notelp=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP

    public static final String KEY_MURID_NAMA = "m_nama";
    public static final String KEY_MURID_NOTELP = "m_notelp";
    public static final String KEY_MURID_EMAIL = "m_email";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_NOTELP = "notelp";
    public static final String TAG_EMAIL = "email";
}
