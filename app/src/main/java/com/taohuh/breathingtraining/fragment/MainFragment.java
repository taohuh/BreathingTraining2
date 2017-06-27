package com.taohuh.breathingtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.dao.UserDAO;
import com.taohuh.breathingtraining.model.User;

import org.w3c.dom.Text;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MainFragment extends Fragment {

    public interface FragmentListener{
        void onBtnListUserClicked();
    }

    Bundle bundle;
    TextView tvName, tvAge;
    Button btnHistory, btnStartPractice, btnListUser, btnSetting;

    User currentUser;
    public long userId = -1;
    private UserDAO userDAO;

    public MainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MainFragment newInstance(Bundle bundle) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putBundle("bundleInfoUser",bundle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        bundle = getArguments().getBundle("bundleInfoUser");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvName = (TextView)rootView.findViewById(R.id.tvName);
        tvAge = (TextView)rootView.findViewById(R.id.tvAge);

        btnStartPractice = (Button)rootView.findViewById(R.id.btnStartPractice);
        btnHistory = (Button)rootView.findViewById(R.id.btnHistory);
        btnListUser = (Button)rootView.findViewById(R.id.btnListUser);
        btnSetting = (Button)rootView.findViewById(R.id.btnSetting);

        btnListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentListener listener = (FragmentListener)getActivity();
                listener.onBtnListUserClicked();
            }
        });

        tvName.setText(bundle.getString("name"));
        tvAge.setText(bundle.getString("age"));
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
