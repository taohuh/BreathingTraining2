package com.taohuh.breathingtraining.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.model.User;
import com.taohuh.breathingtraining.view.CustomTextView;
import com.taohuh.breathingtraining.view.UserListItem;

import java.util.List;

/**
 * Created by Administrator on 10/6/2560.
 */

public class UserListAdapter extends BaseAdapter {


    public static final String TAG = "ListUsersAdapter";

    private List<User> mItems;
    private LayoutInflater mInflater;

    public UserListAdapter(Context context, List<User> listUsers) {
        this.setItems(listUsers);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0;
    }

    @Override
    public User getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            v = mInflater.inflate(R.layout.list_item_user, parent, false);
            holder = new ViewHolder();
            holder.tvName = (CustomTextView) v.findViewById(R.id.tvName);
            holder.tvAge = (CustomTextView) v.findViewById(R.id.tvAge);
            holder.tvBPM = (CustomTextView) v.findViewById(R.id.tvBPM);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        User currentItem = getItem(position);
        if (currentItem != null) {
            holder.tvName.setText(currentItem.getName());
            holder.tvAge.setText(Integer.toString(currentItem.getAge()));
            holder.tvBPM.setText(Integer.toString((int) currentItem.getBpmAfterTest()));
        }

        return v;
    }

    public List<User> getItems() {
        return mItems;
    }

    public void setItems(List<User> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        CustomTextView tvName;
        CustomTextView tvAge;
        CustomTextView tvBPM;
    }
}
