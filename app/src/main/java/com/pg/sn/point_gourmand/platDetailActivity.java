package com.pg.sn.point_gourmand;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class platDetailActivity extends AppCompatActivity {

    private PlatDetail platadap ;


    String name;
    private static final String USGS_REQUEST_URL = "https://gourman-fd71b.firebaseio.com/Plats.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_detail);

        ListView platListView = (ListView) findViewById(R.id.listPlatDetail);

        platadap = new PlatDetail(this,new ArrayList<Plat>());

         name = getIntent().getStringExtra("nomType");

        platListView.setAdapter(platadap);



        platDetailAsyncTask task = new platDetailAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }



    private class platDetailAsyncTask extends AsyncTask<String,Void,List<Plat>> {

        @Override
        protected List<Plat> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Plat> p = QueryUtilsPlats.fetchPlatsDetail(urls[0],name);

            return p;
        }



        @Override
        protected void onPostExecute(List<Plat> data) {
            // Clear the adapter of previous earthquake data


            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                platadap.addAll(data);
            }
        }
    }
}
