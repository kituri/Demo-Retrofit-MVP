package com.mvp.mobile.model.base;

import com.mvp.mobile.data.Entry;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by kirijin on 2016/4/25.
 * 网络基础model接口
 */
public interface INetModel {
    public void onDestroy();
    public <T extends Entry> void enqueue(Call<T> call, Callback<T> callback);
}
