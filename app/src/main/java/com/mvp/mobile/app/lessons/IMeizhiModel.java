package com.mvp.mobile.app.lessons;

import com.mvp.mobile.data.ListEntry;
import com.mvp.mobile.model.base.INetModel;
import com.mvp.mobile.model.base.OnNetModelListener;

/**
 * Created by kirijin on 2016/4/25.
 */
public interface IMeizhiModel extends INetModel{

    void getMeizhiList(final boolean clear, final OnNetModelListener<ListEntry> onNetModelListener);
}
