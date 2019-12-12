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

/**
 * Created by macbookpro on 18/05/2018.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(Activity Context , ArrayList<Category> categories) {
        super( Context, 0,categories);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.category_activity, parent, false);
        }

        Category currentCategory = getItem(position);

        TextView name = (TextView) listItem.findViewById(R.id.name);

        name.setText(currentCategory.getName());


        ImageView imag = (ImageView) listItem.findViewById(R.id.urlimage);
        Glide.with(getContext())
                .load(currentCategory.getImage())
                .apply(RequestOptions.circleCropTransform())
                .into(imag);


        return listItem;
    }
}
