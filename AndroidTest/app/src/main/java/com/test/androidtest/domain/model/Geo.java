package com.test.androidtest.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public class Geo {
    @SerializedName("lat") private double latitude;
    @SerializedName("lng") private String longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
