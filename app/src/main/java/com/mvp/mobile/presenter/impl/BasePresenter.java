package com.mvp.mobile.presenter.impl;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;


import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.data.Entry;
import com.mvp.mobile.model.net.WaitCancelListener;
import com.mvp.mobile.presenter.IBasePresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BasePresenter implements IBasePresenter {

    private IBaseView mIBaseView;

    private ProgressDialog mDialog;

    private boolean isOnCreate;

    private ArrayList<Call> mTask = new ArrayList<>();

    public BasePresenter(IBaseView mIBaseView) {
        this.mIBaseView = mIBaseView;
    }

    @Override
    public void onCreate() {
        this.isOnCreate = true;
    }

    @Override
    public void onDestroy() {
        this.isOnCreate = false;
        if (mTask == null) return;
        for (Call item : mTask) {
            item.cancel();
        }
        mTask.clear();
    }

    @Override
    public boolean isDestroy() {
        return isOnCreate;
    }

    @Override
    public void showWaitDialog(String message, final WaitCancelListener listener) {
        if (mIBaseView == null) return;
        if (mDialog == null) {
            mDialog = new ProgressDialog(mIBaseView.getActivity());
        }
        if (mDialog.isShowing()){
            mDialog.setTitle(message);
        } else {
            mDialog.setTitle(message);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (listener != null) listener.onDialogCancel();
                }
            });
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.show();
        }
    }

    @Override
    public void dismiss() {
        if (mDialog != null) mDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mIBaseView.getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Call<Entry> call, Response<Entry> response) {
        if (mIBaseView != null) {
            mIBaseView.onSuccess();
        }
        mTask.remove(call);
    }


    @Override
    public void onNetWorkError() {
        if (mIBaseView != null) {
            mIBaseView.onNetWorkError();
        }
    }

    public <T extends Entry> void enqueue(Call<T> call, Callback<T> callback){
       // mTask.add(callback);
        mTask.add(call);
        call.enqueue(callback);
    }

}
