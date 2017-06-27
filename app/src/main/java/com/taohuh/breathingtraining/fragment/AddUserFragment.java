package com.taohuh.breathingtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.taohuh.breathingtraining.R;

/**
 * Created by Administrator on 12/5/2560.
 */

public class AddUserFragment extends Fragment {

    public interface FragmentListener{
        void onSaveInfoUserClicked(Bundle bundle);
        void onCancelClickedListener();
    }

    Button btnSave, btnCancel;
    EditText edtUserName, edtUserAge;
    String userName, userAge;

    public static AddUserFragment newInstance(){
        AddUserFragment addUserFragment = new AddUserFragment();
        Bundle args = new Bundle();
        addUserFragment.setArguments(args);
        return addUserFragment;
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
        View rootView = inflater.inflate(R.layout.fragment_add_user_screen, container, false);
        initInstance(rootView);

        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    private void initInstance(View rootView){
        btnSave = (Button)rootView.findViewById(R.id.btnSave);
        btnCancel = (Button)rootView.findViewById(R.id.btnCancel);

        edtUserName = (EditText)rootView.findViewById(R.id.edtUserName);
        edtUserAge = (EditText)rootView.findViewById(R.id.edtUserAge);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edtUserName.getText().toString();
                userAge = edtUserAge.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", userName);
                bundle.putString("age", userAge);

                FragmentListener fragmentListener = (FragmentListener)getActivity();
                fragmentListener.onSaveInfoUserClicked(bundle);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentListener listener = (FragmentListener)getActivity();
                listener.onCancelClickedListener();
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
