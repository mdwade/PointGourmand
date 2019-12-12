package com.pg.sn.point_gourmand;

/**
 * Created by macbookpro on 18/05/2018.
 */

public class Plat {
    String name ,  image , type  ;
    int prix ;
    public Plat(String namee , String imaage , String tyype , int price) {
        image = imaage;
        prix = price;
        name = namee;
        type = tyype;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrix() {
        return prix;
    }

    public String getType() {
        return type;
    }

}
