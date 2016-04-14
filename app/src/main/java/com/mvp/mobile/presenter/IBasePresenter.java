package com.mvp.mobile.presenter;


import com.mvp.mobile.data.Entry;
import com.mvp.mobile.model.net.WaitCancelListener;

import retrofit2.Call;
import retrofit2.Response;


public interface IBasePresenter {

    /**
     * 当Activity onCreate时调用
     */
    void onCreate();

    /**
     * 当Activity onDestory时调用
     */
    void onDestroy();

    /**
     * 当前Activity是否destroy
     */
    boolean isDestroy();

    void showWaitDialog(String message, WaitCancelListener listener);

    void dismiss();

    void showToast(String msg);

    /**
     * 当请求成功时候调用
     */
    void onSuccess(Call<Entry> call, Response<Entry> response);

    void onNetWorkError();
}
