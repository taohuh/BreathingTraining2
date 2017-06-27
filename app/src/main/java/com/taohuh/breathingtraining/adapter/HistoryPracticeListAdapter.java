package com.taohuh.breathingtraining.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.taohuh.breathingtraining.R;
import com.taohuh.breathingtraining.model.History;
import com.taohuh.breathingtraining.view.CustomTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pimpakarn.w on 6/27/2017.
 */

public class HistoryPracticeListAdapter extends BaseAdapter {
    public static final String TAG = "BT: ListHistoryPracticesAdapter";

    private List<History> mItems;
    private LayoutInflater mInflater;

    public HistoryPracticeListAdapter(Context context, List<History> listHistories) {
        this.setItems(listHistories);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public History getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getmId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_practice_history_table, parent, false);
            holder = new ViewHolder();
            holder.tvLevel = (CustomTextView) v.findViewById(R.id.tvLevel);
            holder.tvBPMAvg = (CustomTextView) v.findViewById(R.id.tvBPMAvg);
            holder.tvBPMMin = (CustomTextView) v.findViewById(R.id.tvBPMMin);
            holder.tvBPMMax = (CustomTextView) v.findViewById(R.id.tvBPMMax);
            holder.tvDate = (CustomTextView) v.findViewById(R.id.tvDate);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        // fill row data
        History currentItem = getItem(position);
        if(currentItem != null) {

            holder.tvLevel.setText(Long.toString(currentItem.getmPractice()));
            holder.tvBPMAvg.setText(Integer.toString((int) currentItem.getBpmAVG()));
            holder.tvBPMMin.setText(Integer.toString((int) currentItem.getBpmMIN()));
            holder.tvBPMMax.setText(Integer.toString((int) currentItem.getBpmMAX()));
            holder.tvDate.setText(formatDate(currentItem.getDate()));

        }

        return v;
    }

    public List<History> getItems() {
        return mItems;
    }

    public void setItems(List<History> mItems) {
        this.mItems = mItems;
    }

    class ViewHolder {
        CustomTextView tvLevel;
        CustomTextView tvBPMAvg;
        CustomTextView tvBPMMin;
        CustomTextView tvBPMMax;
        CustomTextView tvDate;
    }

    private String formatDate(String date){
        //Change Date format for TextView
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate= null;
        try{
            myDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String finalDate = newDateFormat.format(myDate);


        System.out.println(finalDate + " in formatDate");

        return finalDate;
    }
}
