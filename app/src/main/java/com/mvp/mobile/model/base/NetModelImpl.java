package com.mvp.mobile.model.base;

import com.mvp.mobile.data.Entry;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by kirijin on 2016/4/25.
 */
public class NetModelImpl extends BaseModel implements INetModel{

    private ArrayList<Call> mTask = new ArrayList<>();

    @Override
    public void onDestroy() {
        if (mTask == null) return;
        for (Call item : mTask) {
            item.cancel();
        }
        mTask.clear();
    }

    public <T extends Entry> void enqueue(Call<T> call, Callback<T> callback){
        // mTask.add(callback);
        mTask.add(call);
        call.enqueue(callback);
    }


}
