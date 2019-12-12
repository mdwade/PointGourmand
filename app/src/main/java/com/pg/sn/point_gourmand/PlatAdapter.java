package com.pg.sn.point_gourmand;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

/**
 * Created by macbookpro on 18/05/2018.
 */

public class PlatAdapter  extends ArrayAdapter<Plat> {
    public PlatAdapter(Activity Context , ArrayList<Plat> plats) {
        super( Context, 0,plats);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.plat_activite, parent, false);
        }

        Plat currentplat = getItem(position);

        TextView name = (TextView) listItem.findViewById(R.id.nom);

        name.setText(currentplat.getName());


        ImageView imag = (ImageView) listItem.findViewById(R.id.imag);
        Glide.with(getContext())
                .load(currentplat.getImage())
                .apply(fitCenterTransform())
                .into(imag);


        return listItem;
    }
}
