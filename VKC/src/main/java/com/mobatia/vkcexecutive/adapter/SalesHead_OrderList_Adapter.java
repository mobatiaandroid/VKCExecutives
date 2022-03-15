package com.mobatia.vkcexecutive.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobatia.vkcexecutive.R;
import com.mobatia.vkcexecutive.controller.AppController;
import com.mobatia.vkcexecutive.model.SubDealerOrderDetailModel;
import com.mobatia.vkcexecutive.model.SubDealerOrderListModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SalesHead_OrderList_Adapter extends RecyclerView.Adapter<SalesHead_OrderList_Adapter.MyViewHolder> {

    Activity mActivity;

    LayoutInflater mLayoutInflater;
    List<SubDealerOrderListModel> listModel;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView shopName;
        LinearLayout searchLAyout;
        TextView prodId;
        TextView qty;
        TextView size;
        TextView textColor;
        RelativeLayout rel1;
        RelativeLayout rel2;
        RelativeLayout rel3;
        RelativeLayout rel4;

        public MyViewHolder(View view) {
            super(view);

            prodId = (TextView) view.findViewById(R.id.txtProdId);
            size = (TextView) view.findViewById(R.id.txtSize);
            qty = (TextView) view.findViewById(R.id.txtQuantity);
            textColor = (TextView) view.findViewById(R.id.txtColor);
            rel1 = (RelativeLayout) view.findViewById(R.id.rel1);
            rel2 = (RelativeLayout) view.findViewById(R.id.rel2);
            rel3 = (RelativeLayout) view.findViewById(R.id.rel3);
            rel4 = (RelativeLayout) view.findViewById(R.id.rel4);

        }
    }


    public SalesHead_OrderList_Adapter(Activity mActivity,
                                       List<SubDealerOrderListModel> listModel) {
        this.mActivity = mActivity;
        this.listModel = listModel;

    }

    @Override
    public SalesHead_OrderList_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_subdealer_list, parent, false);

        return new SalesHead_OrderList_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return listModel.size();
    }
}