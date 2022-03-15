package com.mobatia.vkcexecutive.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ImageView.ScaleType;

import com.mobatia.vkcexecutive.R;
import com.mobatia.vkcexecutive.activities.DashboardFActivity;
import com.mobatia.vkcexecutive.manager.AppPrefenceManager;
import com.mobatia.vkcexecutive.manager.DisplayManagerScale;
import com.mobatia.vkcexecutive.miscellaneous.VKCUtils;
import com.mobatia.vkcexecutive.model.BrandBannerModel;

public class HomeBrandBannerOneAdapter extends PagerAdapter {

	Activity mActivity;
	View layout;
	int displayHeight;
	int displayWidth;
	ArrayList<BrandBannerModel> brandBannerModels;

	public HomeBrandBannerOneAdapter(Activity mActivity,
			ArrayList<BrandBannerModel> brandBannerModels/*
														 * , DisplayView
														 * displayView
														 */) {
		this.mActivity = mActivity;
		this.brandBannerModels = brandBannerModels;
		setDisplayParam(mActivity);

	}

	private void setDisplayParam(Activity activity) {
		DisplayManagerScale mDisplayManager = new DisplayManagerScale(activity);
		displayHeight = mDisplayManager.getDeviceHeight();
		displayWidth = mDisplayManager.getDeviceWidth();
	}

	@Override
	public int getCount() {
		return brandBannerModels.size();
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		layout = inflater
				.inflate(R.layout.custom_home_offer_banner_pager, null);

		setViews(layout, position);
		((ViewPager) container).addView(layout, 0);

		return layout;
	}

	class WraperClass {
		View view;

		public WraperClass(View view) {
			this.view = view;
		}

		public ImageView getImageBanner() {
			ImageView imageView;

			imageView = (ImageView) view.findViewById(R.id.bannerImageView);
			imageView.setScaleType(ScaleType.CENTER_CROP);

			return imageView;

		}

		public ProgressBar getProgressBar() {
			return (ProgressBar) view.findViewById(R.id.progressBar);

		}

	}

	private void setViews(View view, final int arg0) {
		final WraperClass wraperClass = new WraperClass(view);

		VKCUtils.setImageFromUrl(mActivity, brandBannerModels.get(arg0)
				.getBrandBannerOne().replace(" ", "%20"), wraperClass.getImageBanner(), wraperClass
				.getProgressBar());
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DashboardFActivity.dashboardFActivity.setDisplayView();
				AppPrefenceManager.saveListingOption(mActivity, "4");
				AppPrefenceManager.saveIDsForOffer(mActivity,
						"");
				AppPrefenceManager.saveBrandIdForSearch(mActivity,
						brandBannerModels.get(arg0).getId());
			}
		});

	}



	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

}
