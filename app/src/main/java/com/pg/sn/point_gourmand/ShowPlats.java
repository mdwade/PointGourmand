package com.pg.sn.point_gourmand;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowPlats extends AppCompatActivity {

    private  PlatAdapter adapterPlat ;
    private String name ;
    private static final String USGS_REQUEST_URL = "https://gourman-fd71b.firebaseio.com/Plats.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_plats);

        ListView platListView = (ListView) findViewById(R.id.listPlat);

        adapterPlat = new PlatAdapter(this,new ArrayList<Plat>());

         name = getIntent().getStringExtra("nomType");

        platListView.setAdapter(adapterPlat);

        platListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Plat currenplat = adapterPlat.getItem(position);

                String name = currenplat.getName();

                Intent i = new Intent(ShowPlats.this,platDetailActivity.class);
                i.putExtra("nomType",name);
                startActivity(i);
            }
        });

        platAsyncTask task = new platAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class platAsyncTask extends AsyncTask<String,Void,List<Plat>> {

        @Override
        protected List<Plat> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Plat> p = QueryUtilsPlats.fetchPlats(urls[0],name);

            return p;
        }

        @Override
        protected void onPostExecute(List<Plat> data) {
            // Clear the adapter of previous earthquake data


            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapterPlat.addAll(data);
            }
        }
    }
}
