package com.mvp.mobile.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.presenter.IBasePresenter;
import com.mvp.mobile.presenter.impl.BasePresenter;


public class BaseActivity extends FragmentActivity implements IBaseView {

    public IBasePresenter presenter = getPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (presenter != null) presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }

    @Override
    public Context getActivity() {
        return this;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onNetWorkError() {

    }

    public BasePresenter getPresenter() {
        return new BasePresenter(this);
    }

}
