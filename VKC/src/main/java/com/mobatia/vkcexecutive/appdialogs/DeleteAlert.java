/**
 * 
 */
package com.mobatia.vkcexecutive.appdialogs;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mobatia.vkcexecutive.R;
import com.mobatia.vkcexecutive.constants.VKCDbConstants;
import com.mobatia.vkcexecutive.manager.DataBaseManager;
import com.mobatia.vkcexecutive.model.CartModel;

/**
 * @author mobatia-user
 *
 */
public class DeleteAlert extends Dialog implements android.view.View.OnClickListener,VKCDbConstants{
	
	public Activity mActivity;
	public Dialog d;
	DataBaseManager databaseManager;
	int position;
	ArrayList<CartModel> cartList;

	public DeleteAlert(Activity a) {
		super(a);
		// TODO Auto-generated constructor stub
		this.mActivity = a;
        this.cartList=cartList;
		this.position=position;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_delete_alert);
		init();

	}

	private void init() {

		RelativeLayout relativeDate = (RelativeLayout) findViewById(R.id.datePickerBase);
		databaseManager=new DataBaseManager(mActivity);

		Button buttonSet = (Button) findViewById(R.id.buttonOk);
		buttonSet.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				databaseManager.removeFromDb(TABLE_SHOPPINGCART,
						PRODUCT_ID, cartList
								.get(position).getProdId());

			}
			// alrtDbldr.cancel();

		});
		Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

	}

	@Override
	public void onClick(View v) {

		dismiss();
	}

}
