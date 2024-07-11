package com.sabiantart.jsontosqllite.models;

import com.google.gson.annotations.SerializedName;

public class Cubundle {
    @SerializedName("id")
    private String id;
    @SerializedName("dateprod")
    private String dateprod;
    @SerializedName("unit")
    private String unit;
    @SerializedName("lotnumber")
    private String lotnumber;
    @SerializedName("imexlot")
    private String imexlot;
    @SerializedName("bundlecode")
    private String bundlecode;
    @SerializedName("composite")
    private String composite;
    @SerializedName("grossweight")
    private String grossweight;
    @SerializedName("netweight")
    private String netweight;
    @SerializedName("visualgrade")
    private String visualgrade;
    @SerializedName("chemicalgrade")
    private String chemicalgrade;
    @SerializedName("finalgrade")
    private String finalgrade;
    @SerializedName("location")
    private String location;
    @SerializedName("charge")
    private String charge;
    @SerializedName("dateloaded")
    private String dateloaded;
    @SerializedName("date")
    private String date;
    @SerializedName("iduser")
    private String iduser;
    @SerializedName("seal1")
    private String seal1;
    @SerializedName("seal2")
    private String seal2;

    public String getId() {
        return id;
    }

    public String getDateprod() {
        return dateprod;
    }

    public String getUnit() {
        return unit;
    }

    public String getLotnumber() {
        return lotnumber;
    }

    public String getImexlot() {
        return imexlot;
    }

    public String getBundlecode() {
        return bundlecode;
    }

    public String getComposite() {
        return composite;
    }

    public String getGrossweight() {
        return grossweight;
    }

    public String getNetweight() {
        return netweight;
    }

    public String getVisualgrade() {
        return visualgrade;
    }

    public String getChemicalgrade() {
        return chemicalgrade;
    }

    public String getFinalgrade() {
        return finalgrade;
    }

    public String getLocation() {
        return location;
    }

    public String getCharge() {
        return charge;
    }

    public String getDateloaded() {
        return dateloaded;
    }

    public String getDate() {
        return date;
    }

    public String getIduser() {
        return iduser;
    }

    public String getSeal1() {
        return seal1;
    }

    public String getSeal2() {
        return seal2;
    }
}
