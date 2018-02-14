package com.example.lebeaubafouidizo.rapidews;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lebeau BAFOUIDIZO on 11/02/2018.
 */

public class Voiture {


//    @SerializedName("Serie")
    private String Serie;
    private String Marque;
    private String Km;

    public Voiture(String serie, String marque, String km) {
        serie = serie;
        Marque = marque;
        Km = km;
    }

    public Voiture() {
    }


    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getKm() {
        return Km;
    }

    public void setKm(String km) {
        Km = km;
    }
}
