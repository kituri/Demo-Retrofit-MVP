package com.mvp.mobile.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.data.inject.Injector;
import com.mvp.mobile.presenter.BasePresenter;
import com.mvp.mobile.presenter.BasePresenterImpl;


public class BaseActivity extends FragmentActivity implements IBaseView {

    public BasePresenter presenter = getPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (presenter != null) presenter.onCreate();
        if (savedInstanceState != null) Injector.onRestore(this, savedInstanceState);
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

    public BasePresenterImpl getPresenter() {
        return new BasePresenterImpl(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Injector.save(this, outState);
    }

}
