package com.mvp.mobile.model.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kirijin on 2016/4/12.
 */
public class MobileApi {

    protected static final Object object = new Object();
    static MobileApi sMobileApi = null;
    private Retrofit retrofit;
    //final RetGankMeizhi mIGankMeizhi;
    MobileApi(){
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

    public static MobileApi getInstance() {
        synchronized (object) {
            if (sMobileApi == null) {
                sMobileApi = new MobileApi();
            }
            return sMobileApi;
        }
    }

}
