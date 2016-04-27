package com.mvp.mobile.app.lessons;


import com.mvp.mobile.IBaseView.IBaseView;
import com.mvp.mobile.data.ListEntry;

public interface ILessonsView extends IBaseView {

    void onLoadDataList(ListEntry meizhiData);
}
