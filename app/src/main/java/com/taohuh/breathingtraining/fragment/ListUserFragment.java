package com.taohuh.breathingtraining.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.adapter.UserListAdapter;
import com.taohuh.breathingtraining.dao.UserDAO;
import com.taohuh.breathingtraining.model.User;

import java.util.List;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ListUserFragment extends Fragment {

    public interface FragmentListener{
        void onAddUserListener();
    }

    private UserDAO userDAO;
    private List<User> listUsers;
    private UserListAdapter adapter;

    ImageButton btnAddUser;
    TextView tvEmptyUser;
    ListView listViewUser;


    public ListUserFragment() {
        super();
    }

    public static ListUserFragment newInstance() {
        ListUserFragment fragment = new ListUserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_user, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnAddUser = (ImageButton) rootView.findViewById(R.id.btnAddUser);
        tvEmptyUser = (TextView) rootView.findViewById(R.id.tvEmptyListUser);
        listViewUser = (ListView) rootView.findViewById(R.id.listUser);


        btnAddUser.setOnClickListener(addUserListener);

        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        userDAO = new UserDAO(getContext());
        listUsers = userDAO.getAllUsers();
        if (listUsers != null && !listUsers.isEmpty()) {
            adapter = new UserListAdapter(getContext(), listUsers);
            listViewUser.setAdapter(adapter);
        } else {
            tvEmptyUser.setVisibility(View.VISIBLE);
            listViewUser.setVisibility(View.GONE);
        }
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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }


    /**
     * Listener Zone
     */
    final View.OnClickListener addUserListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentListener listener = (FragmentListener)getActivity();
            listener.onAddUserListener();
        }
    };
}
