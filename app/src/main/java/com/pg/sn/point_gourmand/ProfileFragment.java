package com.pg.sn.point_gourmand;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private  commAdapter adapterComm ;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);


        BdOrder order = new BdOrder(getContext());

        ListView platListView = (ListView) root.findViewById(R.id.lfr);
        List orders = order.getOrder();

        adapterComm = new commAdapter(getActivity(),orders);

        platListView.setAdapter(adapterComm);

        return root;
    }

}
