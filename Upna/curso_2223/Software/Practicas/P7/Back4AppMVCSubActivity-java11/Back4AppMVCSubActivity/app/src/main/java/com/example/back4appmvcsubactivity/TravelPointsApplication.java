package com.example.back4appmvcsubactivity;

import android.app.Application;

import com.example.back4appmvcsubactivity.Collection.CommentCollection;
import com.example.back4appmvcsubactivity.Collection.InterestPointCollection;
import com.example.back4appmvcsubactivity.Model.Comment;
import com.example.back4appmvcsubactivity.Model.InterestPoint;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class TravelPointsApplication extends Application {

    private InterestPointCollection pointsCollection;
    private CommentCollection commentsCollection;

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(InterestPoint.class);
        InterestPoint.registerSubclass(Comment.class);

        pointsCollection = new InterestPointCollection();
        commentsCollection = new CommentCollection();

		Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }

    public InterestPointCollection getCollection()
    {
        return pointsCollection;
    }
    public CommentCollection getCommentsCollection() { return commentsCollection; }
    public InterestPoint getOneInterestPoint(int position) { return pointsCollection.getInterestPoint(position); }
}
