package com.pg.sn.point_gourmand;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

/**
 * Created by macbookpro on 19/05/2018.
 */


public class PlatDetail extends ArrayAdapter<Plat>  {

    ElegantNumberButton number;
    BdOrder bdorder;




    public PlatDetail(Activity Context , ArrayList<Plat> plats) {
        super( Context, 0,  plats);
    }
    Plat currentplat;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.plat_detail, parent, false);
        }

         currentplat = getItem(position);
        bdorder = new BdOrder(getContext());

        TextView name = (TextView) listItem.findViewById(R.id.name_plat);
        name.setText(currentplat.getName());

        int prix =  currentplat.getPrix();


        Button bouton = (Button) listItem.findViewById(R.id.prixx);
        bouton.setText(Integer.toString(prix));

         number = (ElegantNumberButton) listItem.findViewById(R.id.number_button);

        Button add = (Button) listItem.findViewById(R.id.addD);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = currentplat.getType();
                String name = currentplat.getName();
                String quantite = number.getNumber();
                int prix = currentplat.getPrix();
                String price = Integer.toString(prix);
                bdorder.addOrder(name,type,quantite,price);

                Toast.makeText(getContext(),"Ajouter au panier",Toast.LENGTH_SHORT).show();



            }
        });


        ImageView imag = (ImageView) listItem.findViewById(R.id.image_pla);
        Glide.with(getContext())
                .load(currentplat.getImage())
                .apply(fitCenterTransform())
                .into(imag);


        return listItem;
    }


}
