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
import com.mobatia.vkcexecutive.customview.HorizontalListView;
import com.mobatia.vkcexecutive.model.SubDealerOrderDetailModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class SalesHead_OrderDetailAdapter extends RecyclerView.Adapter<SalesHead_OrderDetailAdapter.MyViewHolder> {

    Activity activity;

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


    public SalesHead_OrderDetailAdapter(Activity mActivity) {


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_order_list_4_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SubDealerOrderDetailModel order = AppController.subDealerOrderDetailList.get(position);

        // imgClose = (ImageView) view.findViewById(R.id.imgClose);
        holder.prodId.setText(AppController.subDealerOrderDetailList.get(position)
                .getProductId());
        holder.size.setText(AppController.subDealerOrderDetailList.get(position)
                .getCaseDetail());
        holder.qty.setText(AppController.subDealerOrderDetailList.get(position)
                .getQuantity());
        holder.textColor.setText(AppController.subDealerOrderDetailList.get(position)
                .getColor_name());


        //RelativeLayout rel5 = (RelativeLayout) view.findViewById(R.id.rel5);


        if (position % 2 == 0) {
            holder.rel1.setBackgroundColor(Color.rgb(219, 188, 188));
            holder.rel2.setBackgroundColor(Color.rgb(219, 188, 188));
            holder.rel3.setBackgroundColor(Color.rgb(219, 188, 188));
            holder.rel4.setBackgroundColor(Color.rgb(219, 188, 188));
            //rel5.setBackgroundColor(Color.rgb(219, 188, 188));
        } else {
            holder.rel1.setBackgroundColor(Color.rgb(208, 208, 208));
            holder.rel2.setBackgroundColor(Color.rgb(208, 208, 208));
            holder.rel3.setBackgroundColor(Color.rgb(208, 208, 208));
            holder.rel4.setBackgroundColor(Color.rgb(208, 208, 208));
            //rel5.setBackgroundColor(Color.rgb(208, 208, 208));
        }


    }

    @Override
    public int getItemCount() {
        return AppController.subDealerOrderDetailList.size();
    }
}