package com.mvp.mobile.presenter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.data.Entry;
import com.mvp.mobile.model.base.INetModel;
import com.mvp.mobile.model.net.WaitCancelListener;

import java.util.ArrayList;


public class BasePresenterImpl implements BasePresenter {

    private IBaseView mIBaseView;

    private ProgressDialog mDialog;

    private boolean isOnCreate;

    private ArrayList<INetModel> mNetModels = new ArrayList<>();

    public BasePresenterImpl(IBaseView mIBaseView) {
        this.mIBaseView = mIBaseView;
    }

    @Override
    public void onCreate() {
        this.isOnCreate = true;
    }

    @Override
    public void onDestroy() {
        this.isOnCreate = false;
        if (mNetModels == null) return;
        for (INetModel item : mNetModels) {
            item.onDestroy();
        }
        mNetModels.clear();
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
        Toast.makeText(mIBaseView.getActivity(), msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Entry response) {
        if (mIBaseView != null) {
            mIBaseView.onSuccess();
        }
    }


    @Override
    public void onNetWorkError() {
        if (mIBaseView != null) {
            mIBaseView.onNetWorkError();
        }
    }

    public void addNetModel(INetModel... netModels){
        if(netModels == null || netModels.length == 0){
            return;
        }
        for (INetModel model : netModels){
            mNetModels.add(model);
        }
    }

    public void onNetModelNetWorkError() {
        onNetWorkError();
    }


}
