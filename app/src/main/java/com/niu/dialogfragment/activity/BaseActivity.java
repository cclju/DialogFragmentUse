package com.niu.dialogfragment.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.niu.dialogfragment.widget.BaseDialogFragment;
import com.niu.dialogfragment.widget.DialogFactory;


public class BaseActivity extends FragmentActivity  {

    protected DialogFactory mDialogFactory;
    private BaseDialogFragment.BaseDialogListener mDialogListener;

    public BaseDialogFragment.BaseDialogListener getDialogListener(){
        return mDialogListener;
    }

    public void setDialogListener(BaseDialogFragment.BaseDialogListener listener){
        mDialogListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogFactory = new DialogFactory(this, getSupportFragmentManager());
    }

    public void clearDialogListener() {
        mDialogListener = null;
    }
}
