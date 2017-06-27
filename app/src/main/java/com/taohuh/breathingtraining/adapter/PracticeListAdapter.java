package com.taohuh.breathingtraining.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.model.Practice;
import com.taohuh.breathingtraining.view.CustomTextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class PracticeListAdapter extends BaseAdapter {
    public static final String TAG = "ListPracticesAdapter";

    private List<Practice> mItems;
    private LayoutInflater mInflater;

    public PracticeListAdapter(Context context, List<Practice> listPractices) {
        this.setItems(listPractices);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Practice getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_practice, parent, false);
            holder = new ViewHolder();
            holder.tvName = (CustomTextView) v.findViewById(R.id.tvName);
            holder.tvLevel = (CustomTextView) v.findViewById(R.id.tvLevel);
            holder.tvScore = (CustomTextView) v.findViewById(R.id.tvScore);
            holder.tvDate = (CustomTextView) v.findViewById(R.id.tvDate);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        Practice currentItem = getItem(position);
        if(currentItem != null) {
            holder.tvName.setText(currentItem.getmUser().getName());
            holder.tvLevel.setText(Integer.toString(currentItem.getLevelGame()));
            holder.tvScore.setText(Integer.toString(currentItem.getBpmResult()));
            holder.tvDate.setText(formatDate(currentItem.getDate()));
        }

        return v;
    }

    public List<Practice> getItems() {
        return mItems;
    }

    public void setItems(List<Practice> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        CustomTextView tvName;
        CustomTextView tvLevel;
        CustomTextView tvScore;
        CustomTextView tvDate;
    }

    private String formatDate(String date){
        //Change Date format for TextView
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("newDateFormat " + newDateFormat.format(date));
        String strDate = newDateFormat.format(date) ;
        return strDate;
    }
}
