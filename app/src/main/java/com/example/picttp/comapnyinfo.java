package com.example.picttp;

public class comapnyinfo {

    public String Name_Comp,Ctc,Type,Role,Url;

    public comapnyinfo() {
    }

    public String getName_Comp() {
        return Name_Comp;
    }

    public void setName_Comp(String name_Comp) {
        Name_Comp = name_Comp;
    }

    public String getCtc() {
        return Ctc;
    }

    public void setCtc(String ctc) {
        Ctc = ctc;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public comapnyinfo(String name_Comp, String ctc, String type, String role, String url) {
        Name_Comp = name_Comp;
        Ctc = ctc;
        Type = type;
        Role = role;
        Url = url;
    }
}
