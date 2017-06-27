package com.taohuh.breathingtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taohuh.breathingtraining.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ResultRecordBPMFragment extends Fragment {

    public interface FragmentListener{
        void onButtonOKListener();
    }

    Button btnOK, btnRecordBPMAgain;

    public ResultRecordBPMFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ResultRecordBPMFragment newInstance() {
        ResultRecordBPMFragment fragment = new ResultRecordBPMFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result_record_bpm, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnOK = (Button)rootView.findViewById(R.id.btnOK);
        btnRecordBPMAgain = (Button)rootView.findViewById(R.id.btnRecordBPMAgain);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentListener listener = (FragmentListener)getActivity();
                listener.onButtonOKListener();
            }
        });

        btnRecordBPMAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Back to Record Breath Activity
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
