package com.niu.dialogfragment.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.niu.dialogfragment.activity.BaseActivity;
import com.niu.dialogfragment.widget.BaseDialogFragment;
import com.niu.dialogfragment.widget.DialogFactory;


/**

 */
public class BaseFragment extends Fragment {

    protected BaseActivity mBaseActivity;
    protected DialogFactory mDialogFactory;
    private BaseDialogFragment.BaseDialogListener mDialogListener;

    public BaseDialogFragment.BaseDialogListener getDialogListener() {
        return mDialogListener;
    }

    public void setDialogListener(BaseDialogFragment.BaseDialogListener listener) {
        mDialogListener = listener;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialogFactory = new DialogFactory(this, getChildFragmentManager());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            mBaseActivity = (BaseActivity) context;
        }
    }

    public void clearDialogListener() {
        mDialogListener = null;
    }
}
