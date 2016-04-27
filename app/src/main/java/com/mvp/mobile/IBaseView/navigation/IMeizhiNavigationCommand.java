package com.mvp.mobile.IBaseView.navigation;

import com.mvp.mobile.data.MeizhiData;

/**
 * Created by kirijin on 2016/4/25.
 */
public interface IMeizhiNavigationCommand extends INavigationCommand{
    //带参跳转请按照以上继承
    void setMeizhi(MeizhiData.Results meizhi);
}
