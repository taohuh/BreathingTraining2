package com.taohuh.breathingtraining.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;

import com.taohuh.breathingtraining.adapter.UserListAdapter;
import com.taohuh.breathingtraining.dao.UserDAO;
import com.taohuh.breathingtraining.fragment.EmptyUserFragment;
import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.fragment.ListUserFragment;
import com.taohuh.breathingtraining.model.User;

import java.util.List;

public class ListUserActivity extends AppCompatActivity implements ListUserFragment.FragmentListener {

    private static final String TAG = "BT: ListUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        initInstances();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ListUserFragment.newInstance(), "EmptyUserFragment")
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initInstances() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save thing(s) here
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore thing(s) here
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if(savedInstanceState == null){
        }
    }

    @Override
    public void onAddUserListener() {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }
}
