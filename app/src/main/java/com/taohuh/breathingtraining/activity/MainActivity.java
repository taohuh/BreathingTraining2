package com.taohuh.breathingtraining.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener{

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundleInfoUser");


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance(bundle))
                    .commit();
        }
    }

    @Override
    public void onBtnListUserClicked() {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
}
