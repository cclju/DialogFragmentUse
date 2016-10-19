package com.niu.dialogfragment.widget;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.niu.dialogfragment.activity.BaseActivity;
import com.niu.dialogfragment.fragment.BaseFragment;


/**
 * Created by niuxiaowei on 2016/2/3.
 * 对话框工厂
 */
public class DialogFactory {

    /**
     * 进度条的tag
     */
    private static final String DIALOG_PROGRESS_TAG = "progress";

    private static final String DIALOG_CONFIRM_TAG = "confirm";
    private static final String DIALOG_LIST_TAG = "list";

    private FragmentManager mFragmentManager;
    private BaseActivity mBaseActivity;
    private BaseFragment mBaseFragment;

    public DialogFactory(BaseActivity baseActivity, FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        this.mBaseActivity = baseActivity;
    }

    public DialogFactory(BaseFragment baseFragment, FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        this.mBaseFragment = baseFragment;
    }


    /**
     * @param message    进度条显示的信息
     * @param cancelable 点击空白处是否可以取消
     */
    public void showProgressDialog(String message, boolean cancelable) {
        if (mFragmentManager != null) {

            /**
             * 为了不重复显示dialog，在显示对话框之前移除正在显示的对话框。
             */
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_PROGRESS_TAG);
            if (null != fragment) {
                ft.remove(fragment).commit();
            }

            ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance(message, cancelable);
            progressDialogFragment.show(mFragmentManager, DIALOG_PROGRESS_TAG);

            mFragmentManager.executePendingTransactions();
        }
    }

    /**
     * 取消进度条
     */
    public void dissProgressDialog() {
        Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_PROGRESS_TAG);
        if (null != fragment) {
            ((ProgressDialogFragment) fragment).dismiss();
            mFragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    /**
     * @param title      对话框title
     * @param message
     * @param cancelable
     * @param listener
     */
    public void showConfirmDialog(String title, String message, boolean cancelable, ConfirmDialogFragment.ConfirmDialogListener listener) {

        setDialogListener(listener);

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_CONFIRM_TAG);
        if (null != fragment) {
            ft.remove(fragment);
        }
        DialogFragment df = ConfirmDialogFragment.newInstance(title, message, cancelable);
        df.show(mFragmentManager, DIALOG_CONFIRM_TAG);
        mFragmentManager.executePendingTransactions();
    }

    /**
     * 显示列表对话框
     *
     * @param items
     * @param cancelable
     * @param listener
     */
    public void showListDialog(String[] items, boolean cancelable, ListDialogFragment.ListDialogListener listener) {

        setDialogListener(listener);

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragmentManager.findFragmentByTag(DIALOG_LIST_TAG);
        if (null != fragment) {
            ft.remove(fragment);
        }
        DialogFragment df = ListDialogFragment.newInstance(items, cancelable);
        df.show(mFragmentManager, DIALOG_LIST_TAG);
        mFragmentManager.executePendingTransactions();
    }

    private void setDialogListener(BaseDialogFragment.BaseDialogListener listener) {
        if (mBaseActivity != null) {
            mBaseActivity.setDialogListener(listener);
        } else if (mBaseFragment != null) {
            mBaseFragment.setDialogListener(listener);
        }
    }
}
