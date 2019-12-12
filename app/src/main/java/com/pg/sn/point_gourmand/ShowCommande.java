package com.pg.sn.point_gourmand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowCommande extends AppCompatActivity {


    private  commAdapter adapterComm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_commande);

            BdOrder order = new BdOrder(this);

            ListView platListView = (ListView) findViewById(R.id.listComm);
            List orders = order.getOrder();

            adapterComm = new commAdapter(this,orders);

            platListView.setAdapter(adapterComm);
    }
}
