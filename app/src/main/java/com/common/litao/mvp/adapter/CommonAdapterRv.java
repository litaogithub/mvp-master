package com.common.litao.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by litao on 6/18/17.
 * 通用adapter
 */
public abstract class CommonAdapterRv<T> extends RecyclerView.Adapter<ViewHolderRv>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public CommonAdapterRv(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public ViewHolderRv onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        ViewHolderRv viewHolder = ViewHolderRv.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderRv holder, int position)
    {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ViewHolderRv holder, T t);

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }
}