package com.mvp.mobile.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.mobile.IBaseView.navigation.IMeizhiNavigationCommand;
import com.mvp.mobile.R;
import com.mvp.mobile.app.able.SelectionListener;
import com.mvp.mobile.app.utils.ImageUtils;
import com.mvp.mobile.data.Entry;
import com.mvp.mobile.data.MeizhiData;

import java.util.List;

public class MeizhiListAdapter extends RecyclerView.Adapter<MeizhiListAdapter.ViewHolder> implements SelectionListener<Entry> {

    public static final String TAG = "MeizhiListAdapter";

    private List<MeizhiData.Results> mList;
    private IMeizhiNavigationCommand mNavigationCommand;


    public MeizhiListAdapter(List<MeizhiData.Results> meizhiList) {
        mList = meizhiList;
    }

    public void setOnNavigationCommand(IMeizhiNavigationCommand navigationCommand){
        this.mNavigationCommand = navigationCommand;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meizhi, parent, false);
        ViewHolder vh = new ViewHolder(v);
        vh.setOnSelectionListener(this);
        return vh;
    }

    @Override
    public void onSelectionChanged(Entry item, boolean selected) {
        if(mNavigationCommand != null){
            mNavigationCommand.setMeizhi((MeizhiData.Results)item);
            mNavigationCommand.navigate();
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        MeizhiData.Results meizhi = mList.get(position);
        int limit = 48;
        String text = meizhi.getDesc().length() > limit ? meizhi.getDesc().substring(0, limit) +
                "..." : meizhi.getDesc();
        viewHolder.meizhi = meizhi;
        viewHolder.tv_title.setText(text);
        viewHolder.card.setTag(meizhi.getDesc());

        ImageUtils.loadImageView(viewHolder.iv_meizhi, meizhi.getUrl());
    }


    @Override public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    @Override public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_meizhi;
        TextView tv_title;
        View card;
        MeizhiData.Results meizhi;
        private SelectionListener mSelectionListener;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            iv_meizhi = (ImageView) card.findViewById(R.id.iv_meizhi);
            tv_title = (TextView) card.findViewById(R.id.tv_title);
            card.setOnClickListener(this);
        }

        public void setOnSelectionListener(SelectionListener selectionListener){
            this.mSelectionListener = selectionListener;
        }

        @Override
        public void onClick(View v) {
            if(mSelectionListener != null){
                mSelectionListener.onSelectionChanged(meizhi, true);
            }
        }
    }

}
