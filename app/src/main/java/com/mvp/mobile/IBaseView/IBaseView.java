package com.mvp.mobile.IBaseView;

import android.content.Context;


public interface IBaseView {

    Context getActivity();

    void onSuccess();

    void onNetWorkError();



}
