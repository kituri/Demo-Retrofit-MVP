package com.mvp.mobile.presenter.impl;

import com.mvp.mobile.IBaseView.IGankView;
import com.mvp.mobile.data.MeizhiData;
import com.mvp.mobile.model.net.GankApi;
import com.mvp.mobile.model.retrofit.RetGankMeizhi;
import com.mvp.mobile.presenter.IGankPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GankPresenter extends BasePresenter implements IGankPresenter {

    private IGankView mIGankView;
    private Call<MeizhiData> meizhi;

    public GankPresenter(IGankView mIBaseView) {
        super(mIBaseView);
        this.mIGankView = mIBaseView;
    }

    @Override
    public void getMeizhiList(int page) {
        meizhi = GankApi.getGankIOSingleton().getService(RetGankMeizhi.class).getMeizhiList(page);
        enqueue(meizhi, new Callback<MeizhiData>() {
            @Override
            public void onResponse(Call<MeizhiData> call, Response<MeizhiData> response) {
                mIGankView.onMeizhiSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MeizhiData> call, Throwable t) {

            }
        });
    }

}
