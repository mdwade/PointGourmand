package com.pg.sn.point_gourmand;

/**
 * Created by macbookpro on 19/05/2018.
 */

public class Order {
    private String typeProduit ;
    private String nomProduit ;
    private String prix ;
    private String quantity ;

    public Order(String type,String nom,String prii, String Quantity) {
        nomProduit = nom;
        typeProduit = type;
        prix = prii;
        quantity = Quantity;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public String getPrix() {
        return prix;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTypeProduit() {
        return typeProduit;
    }
}
