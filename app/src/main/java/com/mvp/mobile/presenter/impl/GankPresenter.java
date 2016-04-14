package com.mvp.mobile.presenter.impl;

import com.mvp.mobile.IBaseView.IGankView;
import com.mvp.mobile.data.MeizhiData;
import com.mvp.mobile.model.net.GankApi;
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
        meizhi = GankApi.getGankIOSingleton().getGankService().getMeizhiList(page);
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

//    @Override
//    public void doLogin(String userName, String pwd) {
//        //TODO CHECK PARAMS
//        Log.i(MyHttp.TAG,"发起登录请求");
//        executeSync(new LoginModel(userName,pwd).setMyHttpListener(new MyHttpListener<BaseEntity>(this) {
//
//            @Override
//            public void onSuccessOk(BaseEntity s, Response<BaseEntity> response) {
//                super.onSuccessOk(s, response);
//                mILoginView.onLoginSuccess();
//                Log.i(MyHttp.TAG,"response" + response.getResult());
//            }
//        }));
//    }


}
