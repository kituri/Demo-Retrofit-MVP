package com.mvp.mobile.app.lessons;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mvp.mobile.IBaseView.navigation.IMeizhiNavigationCommand;
import com.mvp.mobile.R;
import com.mvp.mobile.app.MobileApplication;
import com.mvp.mobile.data.Entry;
import com.mvp.mobile.data.ListEntry;
import com.mvp.mobile.data.MeizhiData;
import com.mvp.mobile.ui.adapter.MeizhiListAdapter;
import com.mvp.mobile.ui.base.SwipeRefreshFragment;

import java.util.ArrayList;
import java.util.List;


public class LessonsFragment extends SwipeRefreshFragment implements ILessonsView, IMeizhiNavigationCommand {

    private View fragmentLayout;

    private RecyclerView rv_list;
    private MeizhiListAdapter mMeizhiListAdapter;
    private List<MeizhiData.Results> mMeizhiList = new ArrayList<>();

    ILessonsPresenter mPresenter = getPresenter();

    static public final String TAG = "LessonsFragment";
    private boolean mIsFirstTimeTouchBottom = true;
    private MeizhiData.Results meizhi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLayout = inflater.inflate(R.layout.fr_lessons, container, false);
        rv_list = (RecyclerView) fragmentLayout.findViewById(R.id.rv_list);
        setupRecyclerView();
        requestDataRefresh();
        return fragmentLayout;
    }

    public LessonsPresenterImpl getPresenter() {
        return new LessonsPresenterImpl(this);
    }

    @Override
    public void onLoadDataList(ListEntry meizhiData) {
        mMeizhiList.clear();
        for (Entry entry : meizhiData.getEntries()){
            mMeizhiList.add((MeizhiData.Results) entry);
        }
        mMeizhiListAdapter.notifyDataSetChanged();
        setRefreshing(false);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_list.setLayoutManager(linearLayoutManager);
        mMeizhiListAdapter = new MeizhiListAdapter(mMeizhiList);
        mMeizhiListAdapter.setOnNavigationCommand(this);
        rv_list.setAdapter(mMeizhiListAdapter);
        rv_list.addOnScrollListener(getOnBottomListener(linearLayoutManager));
    }

    RecyclerView.OnScrollListener getOnBottomListener(final LinearLayoutManager layoutManager){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 1 && dy > 0 && !mSwipeRefreshLayout.isRefreshing()) {
                    if (!mIsFirstTimeTouchBottom) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        mPresenter.loadDataList(false);
                    }else{
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    @Override public void requestDataRefresh() {
        super.requestDataRefresh();
        mPresenter.loadDataList(true);
    }

    @Override
    public void setMeizhi(MeizhiData.Results meizhi) {
        this.meizhi = meizhi;
    }

    @Override
    public void navigate() {
        if(meizhi != null){
            Toast.makeText(MobileApplication.getInstance(), meizhi.getDesc(), Toast.LENGTH_SHORT).show();
        }
    }
}
