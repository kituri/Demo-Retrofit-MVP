package com.mvp.mobile.app.lessons;

import com.mvp.mobile.data.ListEntry;
import com.mvp.mobile.data.MeizhiData;
import com.mvp.mobile.model.base.NetModelImpl;
import com.mvp.mobile.model.base.OnNetModelListener;
import com.mvp.mobile.model.net.MobileApi;
import com.mvp.mobile.model.retrofit.RetGankMeizhi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kirijin on 2016/4/25.
 */
public class MeizhiModelImpl extends NetModelImpl implements IMeizhiModel {

    private int mPage = 1;
    private ListEntry mMeizhiList = new ListEntry();

    @Override
    public void getMeizhiList(final boolean clear, final OnNetModelListener<ListEntry> onNetModelListener) {
        if(clear){
            mPage = 1;
        }else{
            mPage += 1;
        }
        enqueue(MobileApi.getInstance().getService(RetGankMeizhi.class).getMeizhiList(mPage), new Callback<MeizhiData>() {
            @Override
            public void onResponse(Call<MeizhiData> call, Response<MeizhiData> response) {
                if(response.isSuccessful() && onNetModelListener != null){
                    if(clear){
                        mMeizhiList.clear();
                    }
                    mMeizhiList.addAll(response.body().getResults());
                    onNetModelListener.onNetModelSuccess(mMeizhiList);
                }
            }

            @Override
            public void onFailure(Call<MeizhiData> call, Throwable t) {
                if(onNetModelListener != null){
                    onNetModelListener.onNetModelNetWorkError();
                }
            }
        });
    }
}
