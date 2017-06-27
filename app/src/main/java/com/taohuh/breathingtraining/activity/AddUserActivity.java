package com.taohuh.breathingtraining.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.fragment.AddUserFragment;
import com.taohuh.breathingtraining.fragment.RecordBreathFragment;

public class AddUserActivity extends AppCompatActivity implements AddUserFragment.FragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, AddUserFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onSaveInfoUserClicked(Bundle bundle) {
        Intent intent = new Intent(this, RecordBreathActivity.class);
        intent.putExtra("bundleInfoUser", bundle);
        startActivity(intent);
    }

    @Override
    public void onCancelClickedListener() {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
}
