package com.mvp.mobile.presenter;


import com.mvp.mobile.data.Entry;
import com.mvp.mobile.model.net.WaitCancelListener;


public interface BasePresenter {

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
    void onSuccess(Entry response);

    void onNetWorkError();
}
