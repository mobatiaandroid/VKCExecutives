package com.mobatia.vkcexecutive.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobatia.vkcexecutive.R;
import com.mobatia.vkcexecutive.customview.CustomToast;
import com.mobatia.vkcexecutive.manager.AppPrefenceManager;
import com.mobatia.vkcexecutive.model.CartModel;
import com.mobatia.vkcexecutive.model.ColorModel;
import com.mobatia.vkcexecutive.model.Pending_Order_Model;

public class PendingOrderAdapterApproved extends BaseAdapter {

	Activity mActivity;
	LayoutInflater mInflater;
	ArrayList<CartModel> cartArrayList;
	ArrayList<ColorModel> colorArrayList = new ArrayList<ColorModel>();
	ImageView imgClose;
	LinearLayout linearLayout;
	ArrayList<Pending_Order_Model> listPending;
	private TextView mTxtViewQty;

	static String value;

	public PendingOrderAdapterApproved(Activity mActivity,
			ArrayList<Pending_Order_Model> listPending) {
		this.mActivity = mActivity;
		// this.cartArrayList = cartArrayList;
		// this.linearLayout = linearLayout;
		// mTxtViewQty = txtViewQty;
		this.listPending = listPending;
		mInflater = LayoutInflater.from(mActivity);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listPending.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final CustomToast toast = new CustomToast(mActivity);
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {

			view = mInflater.inflate(R.layout.item_pending_order, null);
			holder = new ViewHolder();

			holder.approvedQty = (TextView) view.findViewById(R.id.txtApproved);
			holder.prodId = (TextView) view.findViewById(R.id.txtProdId);
			holder.size = (TextView) view.findViewById(R.id.txtSize);
			holder.qty = (TextView) view.findViewById(R.id.txtQuantity);
			holder.textColor = (TextView) view.findViewById(R.id.txtColor);

			// TextView color=(TextView)view.findViewById(R.id.txtColor);
			/*
			 * HorizontalListView relColor = (HorizontalListView) view
			 * .findViewById(R.id.listViewColor);
			 */
			// imgClose = (ImageView) view.findViewById(R.id.imgClose);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.prodId.setText(listPending.get(position).getProductId());
		holder.size.setText(listPending.get(position).getCaseDetail());

		holder.qty.setText(listPending.get(position).getOrdered_quantity());

		holder.textColor.setText(listPending.get(position).getColor_name());

		holder.approvedQty.setText(listPending.get(position).getQuantity());
		/*
		 * AppController.subDealerOrderDetailList.get(position) .getColorId(),
		 * 0));
		 */
		RelativeLayout rel1 = (RelativeLayout) view.findViewById(R.id.rel1);
		RelativeLayout rel2 = (RelativeLayout) view.findViewById(R.id.rel2);
		RelativeLayout rel3 = (RelativeLayout) view.findViewById(R.id.rel3);
		RelativeLayout rel4 = (RelativeLayout) view.findViewById(R.id.rel4);
		RelativeLayout rel5 = (RelativeLayout) view.findViewById(R.id.rel5);

		/* Bibin Edited */
		if (AppPrefenceManager.getUserType(mActivity).equals("7")) {
			rel5.setVisibility(View.GONE);
		} else {
			rel5.setVisibility(View.VISIBLE);
		}

		if (position % 2 == 0) {
			rel1.setBackgroundColor(Color.rgb(219, 188, 188));
			rel2.setBackgroundColor(Color.rgb(219, 188, 188));
			rel3.setBackgroundColor(Color.rgb(219, 188, 188));
			rel4.setBackgroundColor(Color.rgb(219, 188, 188));
			rel5.setBackgroundColor(Color.rgb(219, 188, 188));
		} else {
			rel1.setBackgroundColor(Color.rgb(208, 208, 208));
			rel2.setBackgroundColor(Color.rgb(208, 208, 208));
			rel3.setBackgroundColor(Color.rgb(208, 208, 208));
			rel4.setBackgroundColor(Color.rgb(208, 208, 208));
			rel5.setBackgroundColor(Color.rgb(208, 208, 208));
		}
		if (!AppPrefenceManager.getUserType(mActivity).equals("7")) {

		}

		return view;
	}

	static class ViewHolder {
		TextView prodId;
		TextView size;
		TextView qty;
		TextView textColor;
		TextView approvedQty;
	}
}
