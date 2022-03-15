package com.mobatia.vkcexecutive.activities;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobatia.vkcexecutive.R;
import com.mobatia.vkcexecutive.adapter.SalesHeadOrderDetailAdapter;
import com.mobatia.vkcexecutive.adapter.SalesHead_OrderDetailAdapter;
import com.mobatia.vkcexecutive.constants.VKCDbConstants;
import com.mobatia.vkcexecutive.constants.VKCUrlConstants;
import com.mobatia.vkcexecutive.controller.AppController;
import com.mobatia.vkcexecutive.customview.CustomToast;
import com.mobatia.vkcexecutive.manager.VKCInternetManager;
import com.mobatia.vkcexecutive.manager.VKCInternetManager.ResponseListener;
import com.mobatia.vkcexecutive.miscellaneous.VKCUtils;
import com.mobatia.vkcexecutive.model.SubDealerOrderDetailModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class SalesHeadOrderDetailsActivity extends AppCompatActivity implements
        VKCUrlConstants, VKCDbConstants {
    private RecyclerView mLstView;
    private TextView mOrdrNumbr;
    private TextView mDate, mTotalQuantity;
    private Bundle extras;
    LinearLayout llAppRej, llDispatch;
    private boolean isError;
    private View mView;
    int status = 1;
    String orderNumber;
    CustomToast toast;
    int position;
    int mTotalQty = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_head_order_detail);
        AppController.listErrors.clear();
        initUi();
        final ActionBar abar = getSupportActionBar();

        View viewActionBar = getLayoutInflater().inflate(
                R.layout.actionbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                // Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar
                .findViewById(R.id.actionbar_textview);
        textviewTitle.setText("Orders Details");
        abar.setCustomView(viewActionBar, params);
        abar.setDisplayShowCustomEnabled(true);

        setActionBar();

        extras = getIntent().getExtras();
        if (extras != null) {
            orderNumber = extras.getString("orderNumber");
            position = extras.getInt("position");
            mOrdrNumbr.setText("Order number : " + orderNumber);

        }
        mTotalQty = 0;
        getSalesOrderDetails(orderNumber);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // title/icon
        switch (item.getItemId()) {
            case android.R.id.home:
                mTotalQty = 0;
                finish();
        }
        return (super.onOptionsItemSelected(item));
    }

    private void initUi() {
        llAppRej = (LinearLayout) findViewById(R.id.llAppOrRej);
        llDispatch = (LinearLayout) findViewById(R.id.llDispatch);
        toast = new CustomToast(SalesHeadOrderDetailsActivity.this);
        mLstView = (RecyclerView) findViewById(R.id.recyclerOrder);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SalesHeadOrderDetailsActivity.this);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(SalesHeadOrderDetailsActivity.this, DividerItemDecoration.HORIZONTAL);
        mLstView.setLayoutManager(mLayoutManager);
        mLstView.setItemAnimator(new DefaultItemAnimator());
        mLstView.addItemDecoration(itemDecorator);
        mOrdrNumbr = (TextView) findViewById(R.id.txtViewOrder);
        mOrdrNumbr.setVisibility(View.VISIBLE);
        mDate = (TextView) findViewById(R.id.txtViewDate);
        mTotalQuantity = (TextView) findViewById(R.id.totalQty);
        // mDate.setVisibility(View.VISIBLE);
        mView = findViewById(R.id.view);
        mView.setVisibility(View.VISIBLE);

    }

    @SuppressLint("NewApi")
    public void setActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("");
        actionBar.setTitle("");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        actionBar.show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void getSalesOrderDetails(String ordrNo) {
        AppController.TempSubDealerOrderDetailList.clear();
        AppController.subDealerOrderDetailList.clear();

        String name[] = {"order_id"};
        String values[] = {ordrNo};

        final VKCInternetManager manager = new VKCInternetManager(
                SUBDEALER_ORDER_DETAILS);
        manager.getResponsePOST(this, name, values, new ResponseListener() {

            @Override
            public void responseSuccess(String successResponse) {
                parseResponse(successResponse);

            }

            @Override
            public void responseFailure(String failureResponse) {
                VKCUtils.showtoast(SalesHeadOrderDetailsActivity.this, 17);
                isError = true;
            }
        });
    }

    private void parseResponse(String result) {
        try {

            JSONArray arrayOrders = null;
            JSONObject jsonObjectresponse = new JSONObject(result);
            JSONObject response = jsonObjectresponse.getJSONObject("response");
            String status = response.getString("status");
            if (status.equals("Success")) {
                arrayOrders = response.optJSONArray("orderdetails");
                for (int i = 0; i < arrayOrders.length(); i++) {
                    JSONObject obj = arrayOrders.getJSONObject(i);
                    SubDealerOrderDetailModel orderModel = new SubDealerOrderDetailModel();
                    orderModel.setCaseId(obj.getString("case_id"));
                    orderModel.setColorId(obj.getString("color_id"));
                    orderModel.setOrderDate(obj.getString("order_date"));
                    orderModel.setProductId(obj.getString("product_id"));
                    orderModel.setQuantity(obj.getString("quantity"));
                    orderModel.setCaseDetail(obj.getString("caseName"));
                    orderModel.setDetailid(obj.getString("detailid"));
                    orderModel.setColor_code(obj.getString("color_code"));
                    orderModel.setColor_name(obj.getString("color_name"));
                    AppController.subDealerOrderDetailList.add(orderModel);
                    AppController.TempSubDealerOrderDetailList.add(orderModel);

                }

                SalesHead_OrderDetailAdapter mSalesAdapter = new SalesHead_OrderDetailAdapter(
                        SalesHeadOrderDetailsActivity.this);
                mLstView.setAdapter(mSalesAdapter);
                mTotalQty = 0;
                for (int j = 0; j < AppController.TempSubDealerOrderDetailList
                        .size(); j++) {

                    mTotalQty = mTotalQty
                            + Integer
                            .parseInt(AppController.TempSubDealerOrderDetailList
                                    .get(j).getQuantity());
                }

                mTotalQuantity.setText(String.valueOf(mTotalQty));

            }

        } catch (Exception e) {
            isError = true;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        mTotalQty = 0;
    }

}
