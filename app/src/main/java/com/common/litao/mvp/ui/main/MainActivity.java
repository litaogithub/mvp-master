package com.common.litao.mvp.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.common.litao.mvp.R;
import com.common.litao.mvp.adapter.BaseRecyclerAdapter;
import com.common.litao.mvp.adapter.BaseViewHolder;
import com.common.litao.mvp.adapter.callback.SimpleItemTouchHelperCallback;
import com.common.litao.mvp.adapter.listener.RequestLoadMoreListener;
import com.common.litao.mvp.adapter.view.LoadType;
import com.common.litao.mvp.base.BaseActivity;
import com.common.litao.mvp.bean.MainDto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.lst_employee)
    RecyclerView mRecyclerView;
    private BaseRecyclerAdapter<MainDto> mAdapter;
    private List<MainDto> mDatas = new ArrayList<>();
    private boolean isFirst = true;

    @Override protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override protected void initialize(Bundle savedInstanceState) {
        setBarTitle("主页");
        setBackBtn();
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = getAdapter());
        mAdapter.openLoadAnimation(false);
        mAdapter.setLoadMoreType(LoadType.CUBES);
        mAdapter.setOnLoadMoreListener(new RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getData().size() >= 50) {
                            mAdapter.notifyDataChangeAfterLoadMore(false);
                            mAdapter.addNoMoreView();
                        } else {
                            presenter.loadEmployees();
                        }
                    }
                },3000);
            }
        });
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mAdapter.setItemTouchHelper(mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public BaseRecyclerAdapter<MainDto> getAdapter() {
        return new BaseRecyclerAdapter<MainDto>(this,mDatas,R.layout.layout_employee_item) {
            @Override
            protected void convert(BaseViewHolder helper, MainDto item) {
                helper.setText(R.id.txt_title,item.getName());
                helper.setText(R.id.txt_desc,item.getDesc());
            }
        };
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.loadEmployees();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String data, int type) {
        mAdapter.notifyDataChangeAfterLoadMore(JSON.parseArray(data,MainDto.class),true);
//        mAdapter.setData(JSON.parseArray(data,MainDto.class));
    }

    @Override
    public void onFailure(String failMsg) {
        toastShort(failMsg);
    }
}
