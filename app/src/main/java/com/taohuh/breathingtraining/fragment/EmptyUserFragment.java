package com.taohuh.breathingtraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taohuh.breathingtraining.R;

/**
 * Created by Administrator on 12/5/2560.
 */

public class EmptyUserFragment extends Fragment {

    private String TAG = "BT: EmptyUserFragment";

    public interface FragmentListener{
        void onAddUserClicked();

    }

    Button btnAddUser;

    public static EmptyUserFragment newInstance(){
        EmptyUserFragment emptyUserFragment = new EmptyUserFragment();

        return emptyUserFragment;
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
        View rootView = inflater.inflate(R.layout.fragment_no_user_screen, container, false);
        initInstance(rootView);

        return rootView;
    }

    private void initInstance(View rootView){
        btnAddUser = (Button) rootView.findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(addUserListener);
    }

    /*******************
     *  Listener Zone
     *******************/
    final View.OnClickListener addUserListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick() addUserListener");
            FragmentListener listener = (FragmentListener) getActivity();
            listener.onAddUserClicked();
        }
    };

}
