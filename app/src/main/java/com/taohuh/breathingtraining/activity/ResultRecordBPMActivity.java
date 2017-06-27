package com.taohuh.breathingtraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.fragment.ResultRecordBPMFragment;

public class ResultRecordBPMActivity extends AppCompatActivity implements ResultRecordBPMFragment.FragmentListener{

    private static String TAG = "BT: ResultRecordBPMAct";
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_record_bpm);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundleInfoUser");

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ResultRecordBPMFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onButtonOKListener() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("bundleInfoUser", bundle);
        startActivity(intent);
    }
}
