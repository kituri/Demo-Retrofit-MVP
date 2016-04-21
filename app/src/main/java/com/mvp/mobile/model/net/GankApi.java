package com.mvp.mobile.model.net;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kirijin on 2016/4/12.
 */
public class GankApi {

    protected static final Object object = new Object();
    static GankApi sGankApi = null;
    private Retrofit retrofit;

    GankApi(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public <T> T getService(Class<T> cls) {
        return retrofit.create(cls);
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
