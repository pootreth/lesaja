package com.example.lesaja;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class Les implements Serializable{
    private String tanggal;
    private String jam;
    private String matpel;
    private String jenjang;
    private String key;
    private String uid;
    public Les()
    {
    }
    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }
    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMatpel() {
        return matpel;
    }
    public void setMatpel(String matpel) {
        this.matpel = matpel;
    }

    public String getJenjang() {
        return jenjang;
    }
    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public String getUID() {
        return uid;
    }
    public void setUID(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String toString()
    {
        return " "+tanggal+"" +
                "" +jam+"" +
                "" +matpel+"" +
                "" +uid+"" +
                "" +jenjang;
    }
    public Les(String tg, String ja, String ma, String je, String ui)
    {
        tanggal=tg;
        jam=ja;
        matpel=ma;
        jenjang=je;
        uid=ui;
    }
}
