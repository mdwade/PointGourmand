package com.pg.sn.point_gourmand;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private CategoryAdapter adapterCategory;
    private static final String USGS_REQUEST_URL = "https://gourman-fd71b.firebaseio.com/Categories.json";




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ListView categorieListView = (ListView) rootView.findViewById(R.id.liste);

        adapterCategory = new CategoryAdapter(getActivity(),new ArrayList<Category>());


        categorieListView.setAdapter(adapterCategory);

        categorieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Category currentCategor = adapterCategory.getItem(position);

                String name = currentCategor.getName();

                Intent i = new Intent(getContext(),ShowPlats.class);
                i.putExtra("nomType",name);
                startActivity(i);
            }
        });

        categorieeAsyncTask task = new categorieeAsyncTask();
        task.execute(USGS_REQUEST_URL);

        return rootView;

    }

    private class categorieeAsyncTask extends AsyncTask<String,Void,List<Category>> {

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
