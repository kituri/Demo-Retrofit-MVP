package com.mvp.mobile.app.lessons;

import com.mvp.mobile.presenter.BasePresenter;

public interface ILessonsPresenter extends BasePresenter {

    void loadDataList(final boolean clear);

}
