package com.mvp.mobile.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.presenter.BasePresenter;
import com.mvp.mobile.presenter.BasePresenterImpl;
import com.mvp.mobile.ui.base.BaseActivity;


public class BaseFragment extends Fragment implements IBaseView {

    public BasePresenter presenter = getPresenter();

    protected BaseActivity baseActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) presenter.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onNetWorkError() {

    }

    public BasePresenterImpl getPresenter() {
        return new BasePresenterImpl(this);
    }

    public BaseActivity getBaseActivity(){
        Activity act = getActivity();
        if(act instanceof BaseActivity){
            return (BaseActivity)act;
        }
        return null;
    }

}
