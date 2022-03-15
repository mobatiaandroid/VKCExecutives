/**
 *
 */
package com.mobatia.vkcexecutive.adapter;

import java.util.ArrayList;

import com.mobatia.vkcexecutive.R;
import com.mobatia.vkcexecutive.controller.AppController;
import com.mobatia.vkcexecutive.model.BrandTypeModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

/**
 * @author archana
 */
public class FilterBrandContentAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private String[] filterListString;
    private ArrayList<BrandTypeModel> filterArrayList;
    public ArrayList<BrandTypeModel> tempArrayList;

    public FilterBrandContentAdapter(Activity mContext, ArrayList<BrandTypeModel> filterArrayList, ArrayList<BrandTypeModel> tempArrayList) {
        this.mContext = mContext;
        this.filterArrayList = filterArrayList;
        this.tempArrayList = tempArrayList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //return filterListString.length;
        return filterArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return filterArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;


        if (convertView == null) {
            view = mInflater.inflate(R.layout.custom_filtercontent, null);


        } else {
            view = convertView;
        }

        final CheckBox chechBoxType = (CheckBox) view.findViewById(R.id.checkBoxType);
        chechBoxType.setText(filterArrayList.get(position).getName());
        chechBoxType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final boolean isChecked = chechBoxType.isChecked();
                if (isChecked) {
                    tempArrayList.add(filterArrayList.get(position));
                    AppController.tempbrandCatArrayList.add(filterArrayList.get(position).getId());
                } else {
                    removeBrandModel(filterArrayList.get(position));
                    AppController.tempbrandCatArrayList.remove(filterArrayList.get(position).getId());
                }
            }
        });


        if (tempArrayList.contains(filterArrayList.get(position))) {
            chechBoxType.setChecked(true);

        } else {
            chechBoxType.setChecked(false);

        }

        return view;
    }

    private void removeBrandModel(BrandTypeModel mBrandTypeModel) {

        for (int i = 0; i < tempArrayList.size(); i++)

        {
            if (mBrandTypeModel.getName().equals(tempArrayList.get(i).getName())) {
                tempArrayList.remove(i);
            }
        }

    }

}
