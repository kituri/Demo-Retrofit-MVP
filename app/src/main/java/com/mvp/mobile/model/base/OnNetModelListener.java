package com.mvp.mobile.model.base;

import com.mvp.mobile.data.Entry;

/**
 * Created by kirijin on 2016/4/26.
 */
public interface OnNetModelListener<T extends Entry> {
    /**
     * 当请求成功时候调用
     */
    void onNetModelSuccess(T entry);

    void onNetModelNetWorkError();
//        /**
//         * 当Presenter onDestory时调用
//         */
//        void onNetModeDestroy();
//
//        void onNetModeCreate();
}