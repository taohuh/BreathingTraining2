package com.taohuh.breathingtraining.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.fragment.RecordBreathFragment;

public class RecordBreathActivity extends AppCompatActivity implements RecordBreathFragment.FragmentListener {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_breath);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundleInfoUser");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, RecordBreathFragment.newInstance())
                    .commit();
        }
    }


    @Override
    public void onSaveResultRecordListener() {
        Intent intent = new Intent(this, ResultRecordBPMActivity.class);
        intent.putExtra("bundleInfoUser", bundle);
        startActivity(intent);
    }

    @Override
    public void onCancelRecordListener() {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
}
