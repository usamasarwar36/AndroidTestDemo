package com.futureuniverse.fgandroidtest.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Usama Sarwar on 3/31/2017.
 */

public class Company {

    /*
    "company": {
        "name": "Romaguera-Crona",
                "catchPhrase": "Multi-layered client-server neural-net",
                "bs": "harness real-time e-markets"
    }*/

    @SerializedName("name") private String name;
    @SerializedName("catchPhrase") private String catchPhrase;
    @SerializedName("bs") private String businessService;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBusinessService() {
        return businessService;
    }

    public void setBusinessService(String businessService) {
        this.businessService = businessService;
    }
}
