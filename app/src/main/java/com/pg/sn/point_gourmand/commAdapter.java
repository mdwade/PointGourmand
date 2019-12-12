package com.pg.sn.point_gourmand;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

/**
 * Created by macbookpro on 19/05/2018.
 */

public class commAdapter extends ArrayAdapter<Order> {
public commAdapter(Activity Context , List order) {
        super( Context, 0,order);
        }



    @Override
public View getView(int position, View convertView, ViewGroup parent){
        View listItem=convertView;
        if(listItem==null){
        listItem= LayoutInflater.from(getContext()).inflate(
        R.layout.showcomm,parent,false);
        }

        Order currentorder=getItem(position);

        TextView name=(TextView)listItem.findViewById(R.id.nomcom);
        name.setText(currentorder.getNomProduit());
        TextView quant=(TextView)listItem.findViewById(R.id.quantitee);
        name.setText(currentorder.getQuantity());
        TextView typ=(TextView)listItem.findViewById(R.id.typecom);
        name.setText(currentorder.getTypeProduit());

        TextView prix=(TextView)listItem.findViewById(R.id.pr);
        prix.setText(currentorder.getPrix());



        return listItem;
        }
}

