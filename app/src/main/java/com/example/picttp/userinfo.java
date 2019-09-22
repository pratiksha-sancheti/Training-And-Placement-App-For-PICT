package com.example.picttp;

import android.widget.EditText;

public class userinfo {
    public String username,userphone,usermis,usermail,userpassword,usercpassword;

    public userinfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUsermis() {
        return usermis;
    }

    public void setUsermis(String usermis) {
        this.usermis = usermis;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsercpassword() {
        return usercpassword;
    }

    public void setUsercpassword(String usercpassword) {
        this.usercpassword = usercpassword;
    }


    public userinfo(String username, String userphone, String usermis, String usermail, String userpassword, String usercpassword) {
        this.username = username;
        this.userphone = userphone;
        this.usermis = usermis;
        this.usermail = usermail;
        this.userpassword = userpassword;
        this.usercpassword = usercpassword;
    }


}
