package com.mvp.mobile.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kituri.demomvp.R;
import com.mvp.mobile.IBaseView.IGankView;
import com.mvp.mobile.data.MeizhiData;
import com.mvp.mobile.presenter.IGankPresenter;
import com.mvp.mobile.presenter.impl.GankPresenter;


public class Mvp00Fragment extends BaseFragment implements View.OnClickListener, IGankView {

    private View fragmentLayout;

    private TextView tv_submit;
    private Button btn_submit;

    IGankPresenter mPresenter = getPresenter();

    static public final String TAG = "Mvp00Fragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLayout = inflater.inflate(R.layout.fr_00, container, false);
        tv_submit = (TextView) fragmentLayout.findViewById(R.id.tv_submit);
        btn_submit = (Button) fragmentLayout.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        return fragmentLayout;
    }

    @Override
    public void onClick(View v) {
        mPresenter.getMeizhiList(1);
    }

    public GankPresenter getPresenter() {
        return new GankPresenter(this);
    }

    @Override
    public void onMeizhiSuccess(MeizhiData meizhiData) {
        tv_submit.setText(meizhiData.getResults().get(0).getUrl());
    }
}
