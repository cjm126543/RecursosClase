package com.example.back4appmvcsubactivity.Collection;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.back4appmvcsubactivity.Model.Comment;
import com.example.back4appmvcsubactivity.Model.InterestPoint;
import com.example.back4appmvcsubactivity.R;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class CommentCollection {

    private List<Comment> local_comments = new ArrayList<>();

    public CommentCollection() { }

    public void getServerCommentsUpdate(InterestPoint parentPoint, ListView listView) {

        ParseQuery<Comment> query = ParseQuery.getQuery("Comment");
        query.whereEqualTo("interestPoint", parentPoint);
        query.findInBackground((objects, e) -> {
            if (e == null) {
                local_comments = objects;
                ArrayAdapter<Comment> commentItemsAdapter = new ArrayAdapter<Comment>(listView.getContext(), R.layout.row_layout, R.id.listText, local_comments);
                listView.setAdapter(commentItemsAdapter);
                commentItemsAdapter.notifyDataSetChanged();
                Log.v("Object query server OK:", "getServerCommentsUpdate()");
            } else {
                Log.v("Error query, reason: " + e.getMessage(), "getServerCommentsUpdate()");
            }
        });
    }

    public void addObjectUpdate(@NonNull Comment aComment, InterestPoint aInterestPoint, ListView listView) {
        aComment.setPunto(aInterestPoint);
        aComment.saveInBackground(e -> {
            if (e == null) {
                local_comments.add(aComment);
                ArrayAdapter<Comment> commentItemsAdapter;
                commentItemsAdapter = (ArrayAdapter<Comment>) listView.getAdapter();
                commentItemsAdapter.notifyDataSetChanged();
                Log.v("Object saved in server:","addObjectUpdate()");
            } else {
                Log.v("Save failed, reason: " + e.getMessage(), "addObjectUpdate()");
            }
        });
    }
}
