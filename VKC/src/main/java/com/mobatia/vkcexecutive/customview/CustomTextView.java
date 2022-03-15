/**
 * 
 */
package com.mobatia.vkcexecutive.customview;

import java.io.Serializable;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author mobatia-user
 * 
 */
public class CustomTextView extends TextView implements Serializable {

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		//super.setTextColor(Color.WHITE);
		init();
	}

	public void init() {

		//setTypeface(null, Typeface.BOLD);
	}

}
