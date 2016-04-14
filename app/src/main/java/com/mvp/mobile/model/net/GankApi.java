package com.mvp.mobile.model.net;

import com.mvp.mobile.model.retrofit.RetGankMeizhi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kirijin on 2016/4/12.
 */
public class GankApi {

    protected static final Object object = new Object();
    static GankApi sGankApi = null;
    final RetGankMeizhi mIGankMeizhi;
    GankApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         mIGankMeizhi = retrofit.create(RetGankMeizhi.class);

    }

    public RetGankMeizhi getGankService() {
        return mIGankMeizhi;
    }

    public static GankApi getGankIOSingleton() {
        synchronized (object) {
            if (sGankApi == null) {
                sGankApi = new GankApi();
            }
            return sGankApi;
        }
    }

}
