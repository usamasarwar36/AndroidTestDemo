package com.futureuniverse.fgandroidtest.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public class Address {

    /*
    "address": {
        "street": "Kulas Light",
                "suite": "Apt. 556",
                "city": "Gwenborough",
                "zipcode": "92998-3874",
                "geo": {
            "lat": "-37.3159",
                    "lng": "81.1496"
        }
    }
    */

    @SerializedName("street") private String street;
    @SerializedName("suite") private String suite;
    @SerializedName("city") private String city;
    @SerializedName("zipcode") private String zipCode;
    @SerializedName("geo") private Geo geo;
}
