package com.taohuh.breathingtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.taohuh.breathingtraining.R;

/**
 * Created by Administrator on 12/5/2560.
 */

public class SettingFragment extends Fragment {

    private Button btnSettingCharacter;
    private Button btnSettingMap;
    private CheckBox checkBoxNoisy;
    private CheckBox checkBox1, checkBox2, checkBox3;

    public static SettingFragment newInstance(){
        SettingFragment settingFragment = new SettingFragment();

        return settingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        initInstances(rootView);

        return rootView;
    }

    private void initInstances(View rootView){
        // findViewById
        btnSettingCharacter = (Button) rootView.findViewById(R.id.btnSettingCharacter);
        btnSettingMap = (Button) rootView.findViewById(R.id.btnSettingMap);
        checkBoxNoisy = (CheckBox) rootView.findViewById(R.id.checkboxNoisy);
        checkBox1 = (CheckBox) rootView.findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) rootView.findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) rootView.findViewById(R.id.checkBox3);

        btnSettingCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open setting character
            }
        });

        btnSettingMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open setting map
            }
        });

        checkBoxNoisy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




}
