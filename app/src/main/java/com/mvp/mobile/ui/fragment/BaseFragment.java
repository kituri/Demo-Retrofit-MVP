package com.mvp.mobile.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.presenter.IBasePresenter;
import com.mvp.mobile.presenter.impl.BasePresenter;
import com.mvp.mobile.ui.BaseActivity;


public class BaseFragment extends Fragment implements IBaseView {

    public IBasePresenter presenter = getPresenter();

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

    public BasePresenter getPresenter() {
        return new BasePresenter(this);
    }

    public BaseActivity getBaseAppCompatActivity(){
        Activity act=getActivity();
        if(act instanceof BaseActivity){
            return (BaseActivity)act;
        }
        return null;
    }

}
