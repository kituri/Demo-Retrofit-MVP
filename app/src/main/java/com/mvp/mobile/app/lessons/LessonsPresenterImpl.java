package com.mvp.mobile.app.lessons;

import com.mvp.mobile.data.ListEntry;
import com.mvp.mobile.model.base.OnNetModelListener;
import com.mvp.mobile.presenter.BasePresenterImpl;


public class LessonsPresenterImpl extends BasePresenterImpl implements ILessonsPresenter, OnNetModelListener<ListEntry> {

    private ILessonsView mILessonsView;
    private IMeizhiModel mIMeizhiModel;

    public LessonsPresenterImpl(ILessonsView mIBaseView) {
        super(mIBaseView);
        this.mILessonsView = mIBaseView;
        this.mIMeizhiModel = new MeizhiModelImpl();
        super.addNetModel(mIMeizhiModel);
    }

    @Override
    public void loadDataList(final boolean clear) {
        mIMeizhiModel.getMeizhiList(clear, this);
    }

    @Override
    public void onNetModelSuccess(ListEntry entry) {
        super.onSuccess(entry);
        mILessonsView.onLoadDataList(entry);
    }

    //重写的情况需要自行处理，必须执行super
//    @Override
//    public void onNetModelNetWorkError() {
//        super.onNetWorkError();
//    }
}
