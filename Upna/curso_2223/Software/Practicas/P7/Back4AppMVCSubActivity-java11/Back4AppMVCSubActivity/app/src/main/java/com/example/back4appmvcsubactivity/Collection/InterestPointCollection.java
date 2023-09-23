package com.example.back4appmvcsubactivity.Collection;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import com.example.back4appmvcsubactivity.Model.InterestPoint;
import com.example.back4appmvcsubactivity.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class InterestPointCollection {

    private List<InterestPoint> local_pointList = new ArrayList<>();

    public InterestPointCollection()
    {
       // ParseObject.registerSubclass(InterestPoint.class); // podría estar aqui
    }

    public InterestPoint getInterestPoint(int pos) { return local_pointList.get(pos); }

    public void getServerPointsUpdate(ListView listView) {

        ParseQuery<InterestPoint> query = ParseQuery.getQuery("InterestPoint");
        query.findInBackground((objects, e) -> {
            if (e == null) {
                local_pointList = objects;
                ArrayAdapter<InterestPoint> pointItemsAdapter =
                        new ArrayAdapter<InterestPoint>(listView.getContext(),R.layout.row_layout,
                                R.id.listText,local_pointList);
                listView.setAdapter(pointItemsAdapter);
                pointItemsAdapter.notifyDataSetChanged();

                Log.d("object query server OK:", "getServerPointsUpdate()");
            } else {
                Log.d("error query, reason: " + e.getMessage(), "getServerPointsUpdate()");
            }
        });
    }

    public void addObjectUpdate(@NonNull InterestPoint aInterestPoint, ListView listView) {

        aInterestPoint.saveInBackground(e -> {
            if (e == null) {
                local_pointList.add(aInterestPoint);
                ArrayAdapter<InterestPoint> pointItemsAdapter;
                pointItemsAdapter = (ArrayAdapter<InterestPoint>) listView.getAdapter();
                pointItemsAdapter.notifyDataSetChanged();
                Log.d("object saved in server:", "addObjectUpdate()");
            } else {
                Log.d("save failed, reason: "+ e.getMessage(), "addObjectUpdate()");
           }
        });
    }

}
