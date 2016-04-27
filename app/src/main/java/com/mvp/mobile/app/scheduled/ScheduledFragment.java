package com.mvp.mobile.app.scheduled;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.mobile.R;
import com.mvp.mobile.ui.fragment.BaseFragment;


public class ScheduledFragment extends BaseFragment {

    private View fragmentLayout;

    static public final String TAG = "ScheduledFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLayout = inflater.inflate(R.layout.fr_scheduled, container, false);
        return fragmentLayout;
    }

}
