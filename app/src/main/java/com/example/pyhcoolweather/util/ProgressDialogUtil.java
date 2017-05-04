package com.example.pyhcoolweather.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2016/9/18.
 */
public class ProgressDialogUtil {
    private  ProgressDialog progressDialog;

    public ProgressDialogUtil(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    public void showProgressDialog(){
        if (progressDialog != null){
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public void closeProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }

}
