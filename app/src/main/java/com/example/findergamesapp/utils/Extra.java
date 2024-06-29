package com.example.findergamesapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Extra {
  Context context;
  ProgressDialog progressDialog;
   public Extra(Context context){
       this.context=context;
       progressDialog=new ProgressDialog(context);
   }
   public void showProgress(String title,String message){
       progressDialog.setTitle(title);
       progressDialog.setMessage(message);
       progressDialog.setCancelable(false);
       progressDialog.show();
   }
   public void cancelProgress(){
       progressDialog.cancel();
   }

}
