package com.pg.sn.point_gourmand;

/**
 * Created by macbookpro on 18/05/2018.
 */

public class Category {
    String name ,  image ;
    public Category(String nom , String urlImage) {
        name = nom;
        image = urlImage;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}

