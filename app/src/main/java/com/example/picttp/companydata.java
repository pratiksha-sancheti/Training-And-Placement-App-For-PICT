package com.example.picttp;

import android.widget.EditText;

public class companydata {
    private String Comp_name,CTC,TYPE,ROLE,URL;

    public companydata() {
    }

    public String getComp_name() {
        return Comp_name;
    }

    public void setComp_name(String comp_name) {
        Comp_name = comp_name;
    }

    public String getCTC() {
        return CTC;
    }

    public void setCTC(String CTC) {
        this.CTC = CTC;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    public companydata(String comp_name, String CTC, String TYPE, String ROLE, String URL) {
        Comp_name = comp_name;
        this.CTC = CTC;
        this.TYPE = TYPE;
        this.ROLE = ROLE;
        this.URL = URL;
    }
}
