package com.pg.sn.point_gourmand;

/**
 * Created by macbookpro on 18/05/2018.
 */

public class User {
    private String name , email ,password ;

    public User(String namee , String emaiil , String passworrd) {
        name=namee;
        email = emaiil;
        password = passworrd;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
