package com.mvp.mobile.model.retrofit;

import com.mvp.mobile.data.MeizhiData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kirijin on 2016/4/12.
 */
//http://gank.io/api/data/福利/10/1
public interface RetGankMeizhi {
    @GET("data/福利/10/{page}")
    Call<MeizhiData> getMeizhiList(@Path("page") int page);
}
