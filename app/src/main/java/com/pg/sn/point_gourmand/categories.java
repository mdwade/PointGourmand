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

public class categories extends AppCompatActivity {

    private CategoryAdapter adapterCategory;
    private static final String USGS_REQUEST_URL = "https://gourman-fd71b.firebaseio.com/Categories.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ListView categorieListView = (ListView) findViewById(R.id.list);

        adapterCategory = new CategoryAdapter(this,new ArrayList<Category>());


        categorieListView.setAdapter(adapterCategory);

        categorieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Category currentCategor = adapterCategory.getItem(position);

                String name = currentCategor.getName();

                Intent i = new Intent(categories.this,ShowPlats.class);
                i.putExtra("nomType",name);
                startActivity(i);
            }
        });

        categorieAsyncTask task = new categorieAsyncTask();
        task.execute(USGS_REQUEST_URL);

    }

    private class categorieAsyncTask extends AsyncTask<String,Void,List<Category>> {

        @Override
        protected List<Category> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Category> p = QueryUtils.fetchCategory(urls[0]);

            return p;
        }

        @Override
        protected void onPostExecute(List<Category> data) {
            // Clear the adapter of previous earthquake data


            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapterCategory.addAll(data);
            }
        }
    }


}
